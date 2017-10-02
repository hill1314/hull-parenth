﻿var AppConst = {
}
StrUtil = window.StrUtil || {
	capitalize:function(string) {
	    return string.charAt(0).toUpperCase() + string.slice(1);
	},
	toInt:function(num){
		if(num == undefined || num == null || num == '' || num == 'NaN'){
			num = '0';
		}
		return parseInt(num);
	},
	cutString:function(str,len){
		var len  = len | 10;
		if(str != null && str != ''){
			if(str.length > len){
				str = str.substring(0,len) + '...';
			}
		}
		return str;
	},
	subStr:function(str, len){
	    if(!str) { return ''; }
	        len = len > 0 ? len*2 : 280;
	    var count = 0,	//计数：中文2字节，英文1字节
	        temp = '';  //临时字符串
	    for (var i = 0;i < str.length;i ++) {
	    	if (str.charCodeAt(i) > 255) {
	        	count += 2;
	        } else {
	        	count ++;
	        }
	        //如果增加计数后长度大于限定长度，就直接返回临时字符串
	        if(count > len) { return temp; }
	        //将当前内容加到临时字符串
	         temp += str.charAt(i);
	    }
	    return str;
	},
	//字符串长度-中文和全角符号为1，英文、数字和半角为0.5
	getLength:function(str, shortUrl) {
		if (true == shortUrl) {
			return Math.ceil(str.replace(/((news|telnet|nttp|file|http|ftp|https):\/\/){1}(([-A-Za-z0-9]+(\.[-A-Za-z0-9]+)*(\.[-A-Za-z]{2,5}))|([0-9]{1,3}(\.[0-9]{1,3}){3}))(:[0-9]*)?(\/[-A-Za-z0-9_\$\.\+\!\*\(\),;:@&=\?\/~\#\%]*)*/ig, 'http://goo.gl/fkKB ')
								.replace(/^\s+|\s+$/ig,'').replace(/[^\x00-\xff]/ig,'xx').length/2);
		} else {
			return Math.ceil(str.replace(/^\s+|\s+$/ig,'').replace(/[^\x00-\xff]/ig,'xx').length/2);
		}
	},
	getUUID:function() {
	    var d = new Date().getTime();
	    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
	        var r = (d + Math.random()*16)%16 | 0;
	        d = Math.floor(d/16);
	        return (c=='x' ? r : (r&0x7|0x8)).toString(16);
	    });
	    return uuid;
	},
	fixNull:function(str){
		return str || '';
	}
}


/**
 * 网络操作
 */
Net = window.Net || {
	get:function(options){
		var defaults = {
			type: 'GET',
		};
		var opts = $.extend(defaults, options);
		this.ajax(opts);
	},
	post:function(options){
		var defaults = {
				type: 'POST',
		};
		var opts = $.extend(defaults, options);
		this.ajax(opts);
	},
	ajax:function(options){
		var opts = {};
		var defaults = {
		   //private params
		   reload:false,
		   go:null,
		   boxClose:false,
		   btn:null,
		   callback:null,
		   
		   //Jquery params
		   type: "GET",
		   dataType:"json",
		   cache: false,
		   url: null,
		   traditional: true,
		   beforeSend:function(){
			   if(opts.btn != null){
				   opts.btn.attr('disabled','disabled');
			   }
		   },
		   complete:function(){
			   if(opts.btn != null){
				   opts.btn.attr('disabled',null);
			   }
           },
		   success: function(resp){
			   switch (resp.status) {
			    case 0:
		            ui.msg.success( resp.msg );
		            if(opts.callback != null && $.isFunction(opts.callback)){
		            	opts.callback();
		            }
		            if(opts.reload){
		            	ui.reload();
		            }
		            if(opts.boxClose){
		            	ui.boxClose();
		            }
		            if(opts.go != null && opts.go != ''){
		            	ui.go( opts.go );
		            }
		            break;
		        case 1:
		            ui.msg.fail( resp.msg );
		            break;
		        case -1:
		        	ui.msg.error( resp.msg );
		        default:
		        	ui.msg.error( resp.msg );
		        		
		      }
		   },
		   error:function(){
			   ui.msg.warning( ui.msg.CONST.MSG_BAD_REQUEST );
		   }
		};
		
		opts = $.extend(defaults, options);
		$.ajax( opts ); 
	},
	ajaxForm:function(options){
		var opts = {};
		var defaults = {
			form: $('#inputForm'),
			btn: $('#sbmBtn'),
			checkForm:true,
			boxClose:false,
			go:null,
	        dataType:"json",
	        beforeSerialize:function(){
	        	return true;
	        },
	        beforeSubmit: function(){
	           if( opts.btn != null ){
	        	   opts.btn.attr("disabled","disabled").addClass("disabled");
	           }
		       return true;
	        },
	        clearForm: false,
	        resetForm: false,
	        success: function(resp){
	        	switch (resp.status) {
				    case 0:
			            ui.msg.success(resp.msg);
			            if(opts.go != null ){
			            	ui.go(opts.go);
			            }
			            break;
			        case 1:
			            ui.msg.fail(resp.msg);
			            break;
			        case -1:
			        	ui.msg.error(resp.msg);
			        default:
			        	ui.msg.error( resp.msg );
		      }
	          if( opts.btn != null ){
	        	  opts.btn.attr("disabled",null).removeClass("disabled");
		      }
	          if(opts.boxClose){
	        	  ui.boxClose();
	          }
	        },
	        error:function(resp){
	        	ui.msg.warning( ui.msg.CONST.MSG_BAD_REQUEST );
	        	
	        	if( opts.btn != null ){
	        		opts.btn.attr("disabled",null).removeClass("disabled");
			    }
	        }
		};
		opts = $.extend(defaults, options);
		
		if(opts.form == null){
			ui.msg.warning( 'FORM NOT FOUND');
			return;
		}
		if(opts.btn == null){
			ui.msg.warning( 'SUBMIT BUTTON NOT FOUND');
			return;
		}
		
		if(opts.checkForm){
			opts.form.checkForm();
		}
		opts.form.ajaxForm(opts);
	}
};

/*
 * 功能：页面通用的UI操作
 */
ui = window.ui ||{
	go:function(url,timeout){
		if(url != null && url != ''){
			timeout = timeout || 1800;
			setTimeout(function(){
				document.location.href = url;
			},timeout);
		}
	},
	to:function(url){
		document.location.href = url;
	},
	reload:function(){
		setTimeout(function(){
			document.location.reload();
		},1800);
	},
	confirm:function(msg,myCallBack,appendHtml){
		var content = '<div class="alert alert-warning text-center"><i class="glyphicon glyphicon-warning-sign fa fa-warning bigger-120"></i> <strong>'+msg+'</strong></div>';
		if(appendHtml!=null&&appendHtml!=''){
			content = content + appendHtml;
		}
		var uibox = $.scojs_confirm({
			  content: content,
			  action: myCallBack
		});
		uibox.show();
		
		//see sco.confirm.js --> id="uiBoxCancellBtn"
		$('#uiBoxCancellBtn').click(function(){
			uibox.destroy();
		});
		$('#uiBoxYeapBtn').click(function(){
			uibox.destroy();
		});
	},
	msg:{
		CONST:{
			MSG_SUCCESS:"operation success",
			MSG_FAIL:"operation fail",
			MSG_INFO:"info",
			MSG_WARNING:"warning",
			MSG_ERROR:"server error",
			MSG_BAD_REQUEST:"request send fail"
		},
		success:function(msg,callback){
			this._showMsg({
				level:'SUCCESS',
				msg:msg,
				callback:callback
			});
		},
		fail:function(msg,callback){
			this._showMsg({
				level:'FAIL',
				msg:msg,
				callback:callback
			});
		},
		info:function(msg,callback){
			this._showMsg({
				level:'INFO',
				msg:msg,
				callback:callback
			});
		},
		warning:function(msg,callback){
			this._showMsg({
				level:'WARNING',
				msg:msg,
				callback:callback
			});
		},
		error:function(msg,callback){
			this._showMsg({
				level:'ERROR',
				msg:msg,
				callback:callback
			});
		},
		_showMsg:function(options){
			var opts = {
				msg:null,
				level:'ERROR',
				callback:null
			}
			opts = $.extend(opts, options);
			//fixed if the first param is function
			if(opts.msg != null && $.isFunction(opts.msg) ){
				opts.callback = opts.msg;
				opts.msg = null;
			}
			
			var _self = this;
			var msgTxt = '';
			
			switch (opts.level) {
				case 'SUCCESS':
					msgTxt = '<div><i class="glyphicon glyphicon-ok-circle fa-ok-circle"></i> '+ (opts.msg || _self.CONST.MSG_SUCCESS) +'</div>';
					$.scojs_message(msgTxt,$.scojs_message.TYPE_OK);
					//smoke.signal(msg,2300);
					break;
				case 'FAIL':
					msgTxt = '<div><i class="glyphicon glyphicon-remove-circle fa-remove-circle"></i> '+ (opts.msg  || _self.CONST.MSG_FAIL)  +'</div>';
					$.scojs_message(msgTxt,$.scojs_message.TYPE_ERROR);
					break;
				case 'INFO':
					msgTxt = '<div><i class="glyphicon glyphicon-exclamation-sign  fa-exclamation-sign"></i> '+ (opts.msg || _self.CONST.MSG_INFO) +'</div>';
					$.scojs_message(msgTxt,$.scojs_message.TYPE_INFO);
					break;
				case 'WARNING':
					msgTxt = '<div><i class="glyphicon glyphicon-warning-sign  fa fa-warning"></i> '+ (opts.msg  || _self.CONST.MSG_WARNING)  +'</div>';
					$.scojs_message(msgTxt,$.scojs_message.TYPE_WARNING);
					break;
				default://FAIL
					msgTxt = '<div><i class="glyphicon glyphicon-remove-circle fa-remove-circle"></i> '+ (opts.msg  || _self.CONST.MSG_ERROR)  +'</div>';
					$.scojs_message(msgTxt,$.scojs_message.TYPE_ERROR);
					break;
			}
			if(opts.callback != null && $.isFunction(opts.callback)){
				opts.callback();
			}
		}
	},
	box:function(options){
		var defaults = {
			title:'page info',
			content:'',
			size: '',   //sm , lg
			backdrop:true,
			keyboard:true,
			show:true,
			footer:true,
			remote:false,  //如果是，则在remote这里填写URL
			method:'GET',
			param:{},    //当remote有值时，需要传递的参数
			btn:''
		};
		var opts = $.extend(defaults, options);
		var jBoxWidth = 650;
		var jBoxHeight = 450;
		
		//close pre box
		if($('.jBox-closeButton').length == 1){
			$('.jBox-closeButton').click();
		}
		
		if(opts.size != ''){
			var sizeClass = '';
			if(opts.size == 'xs' ){
				var jBoxWidth = 300;
				var jBoxHeight = 100;
			}
			if(opts.size == 'sm' ){
				var jBoxWidth = 500;
				var jBoxHeight = 350;
			}
			if(opts.size == 'mid' ){
				var jBoxWidth = 650;
				var jBoxHeight = 450;
			}
			if(opts.size == 'lg' ) {
				var jBoxWidth =  $(window).width() - 300;
				var jBoxHeight = 'auto';
			}
			if(opts.size == 'xlg' ) {
				var jBoxWidth =  $(window).width() - 50;
				var jBoxHeight = 'auto';
			}
			$('#myUIBoxWrapper').addClass( sizeClass );
		};
		
		var ajaxOptions = null;
		if(opts.remote != ''){
			ajaxOptions = {
				type:opts.method,
				dataType:"html",
				cache: false,
				url: opts.remote,
				data: opts.param,
			    reload: false,
			    setContent: true,
			    spinner: true	
			}
			opts.remote = false;
		}

		new jBox('Modal', {
			trigger:'click',
		    width: jBoxWidth,
		    height: jBoxHeight,
		    minWidth: 500,
		    minHeight: 100,
		    maxHeight: $(window).width() - 200,
		    maxHeight: $(window).height() - 150,
		    //animation:{open: 'tada', close: 'flip'},
		    //animation:{open: 'zoomIn', close: 'tada'},
		    overlay:true,
		    closeOnClick: false,
		    appendTo:$('body'),
			draggable:'title',
		    attach: $('#myModal'),
		    title: opts.title,
		    ajax:ajaxOptions,
		    content: opts.content,
		    closeButton:'box',
		    onCreated: function() {
		    	if(opts.footer){
		    		this.footer = jQuery('<div class="jBox-Confirm-footer" style="text-align:right;"/>');
					jQuery('<span>'+opts.btn+'&nbsp;</span>').appendTo(this.footer);
					jQuery('<span style="margin:0 12px;"><a href="javascript:void(0)" class="btn btn-sm btn-danger">&nbsp;Cancel&nbsp;</a></span>').click(function() {this.options.cancel && this.options.cancel(); this.close();}.bind(this)).appendTo(this.footer);
					this.footer.appendTo(this.container);
		    	}
			},
			onCloseComplete: function() {
				this.destroy();
			}
		}).open();
	},
	boxClose:function(){
		$('.jBox-closeButton').click();
	}
};
var checkAll = {
  isChecked:function(){
	  var $chkarry = $('input:checked').not($('#checkedAll')).not($('.checkedAll'));
	  if( $chkarry != null && $chkarry.length > 0){
		  return true;
	  }else{
		  return false;
	  }
  },
  isCheckedOne:function(){
	  var $chkarry = $('input:checked').not($('#checkedAll')).not($('.checkedAll')).not($('#checkAll')).not($('.checkAll'));
	  if( $chkarry != null && $chkarry.length == 1){
		  return true;
	  }else{
		  ui.msg.warning('please select one item!');
		  return false;
	  }
  },
  getId:function(){
	  return $('input:checked').not($('#checkedAll')).not($('.checkedAll')).not($('#checkAll')).not($('.checkAll'));[0].value;
  },
  getIdsAsArr:function(){
	  var $chkarry = $('input:checked').not($('#checkedAll')).not($('.checkedAll')).not($('#checkAll')).not($('.checkAll'));;
	  
	  console.log( $chkarry );
	  
	  var params = new Array();
	  for(var i=0;i<$chkarry.length;i++){
		  params.push( $chkarry[i].value );
	  }
	  return params;
  },
  getIdsAsStr:function(){
	  var $chkarry = $('input:checked').not($('#checkedAll')).not($('.checkedAll')).not($('#checkAll')).not($('.checkAll'));;
	  var params = '';
	  for(var i=0;i<$chkarry.length;i++){
		  if($chkarry[i].value != ''){
			  params += $chkarry[i].value + ",";
		  }
	  }
	  params = params.substring(0,params.length - 1);
	  return params;
  }
}

/**
 * checkAll 
 */
$(function() {  
	//check all ont th 
	$('table th input:checkbox').on('click' , function(){
		var that = this;
		$(this).closest('table').find('tr > td:first-child input:checkbox')
		.each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
	});
});
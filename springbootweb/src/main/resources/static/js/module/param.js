Param = {
	initialize:function(ul){
		var _this = this;
		_this.bindSwitcher();
		_this.bindInsertRow(ul);
		_this.bindRemoveRow(ul);
		_this.bindParamSend(ul);
	},
	bindInsertRow:function(ul){
		var _this = this;
		var _ul = $(ul);
		ul.on('focus','li:last-child input:not(:checkbox)',function(event){
			var li = $(this);
			_ul.append(ParamTemplate.getRowTemplate());
			_ul.find('li div.js-param-optbar').show();
			_ul.find('li:last-child div.js-param-optbar').hide();
			_this.bindSwitcher();
		});
	},
	bindRemoveRow:function(ul){
		ul.on('click','a.js-param-remove',function(){
			$(this).closest('li').remove();
		});
	},
	bindParamSend:function(ul){
		ul.on('click','a.js-param-send',function(){
			var _btn = $(this);
			var checkbox = _btn.closest('li').find('input[name="paramSend"]');
			if( checkbox.val() == 'true'){
				checkbox.val(false);
				_btn.find('span').removeClass('fa-check-square-o').addClass('fa-square-o');
			}else{
				checkbox.val(true);
				_btn.find('span').addClass('fa-check-square-o').removeClass('fa-square-o');
			}
		});
	},
	bindSwitcher:function(){
		$("input[name='paramRequirement']").bootstrapSwitch({
        	size:'small',
        	labelText:'Required',
        	onText:'true',
        	offText:'false',
        	onColor:'danger',
        	offColor:'success'
        });
	}
}

$(function(){
	$('.js-param').each(function(){
		Param.initialize( $(this) );
	})
})
AppGridPrivilege = {
	bindCheckAllEvent:function(){
		$('.checkAll').on('click',function(){
			var checkBox = $(this);
			var isCheck = checkBox[0].checked;
			if(isCheck){
				checkBox.closest("td").find("input[name='id']").prop("checked","checked");
			}else{
				checkBox.closest("td").find("input[name='id']").prop("checked",null);
			}
		});
	},
	addCity:function(id){
		ui.box({
            title:'添加特权城市',
            remote:'/app_grid_privilege_city_add/'+id,
            btn:'<button type="button" class="btn btn-success btn-sm" onclick="$(\'#inputForm\').submit()">提交</button>'
        });
	},
	addTargetCity:function(obj){
		var option = $("option:selected", obj);
		var id = option.val();
		var name = option.text();
		if($('#city_'+id).length == 0){
			$('#inputForm').append('<input type="hidden" id="city_'+id+'" name="privilegeCityIds" value="'+id+'"/>');
			$('#newCitys').append('<span class="label label-info">'+name+' &nbsp;<a href="javascript:void(0)" id="'+id+'"><i class="fa fa-times text-red"></i></a></span> &nbsp;');
		}
	},
	bindCitySelect2Event:function(){
		$('#cityIds').select2();
		$('#cityIds').on('change',function(){
			AppGridPrivilege.addTargetCity(this);
		});
	},
	bindRemoveCityEvent:function(){
		$('#newCitys').on('click','a',function(){
			var _this = $(this);
			var id = _this.prop('id');
			$('#city_'+id).remove();
			_this.closest('span').remove();
		});
	},
	bindFormSubmitEvent:function(){
		Net.ajaxForm({
			boxClose:true
		});
	},
	addPrivilegeUser:function(authorityId){
		ui.box({
            title:'添加特权人员',
            remote:'/app_grid_privilege_user_add',
            param:{
                authorityId:authorityId
            },
            btn:'<button type="button" class="btn btn-success btn-sm" onclick="$(\'#inputForm\').submit()">提交</button>'
        });
	},
    delete:function(id){
        Net.post({
            url:'/app_grid_privilege_delete',
            data:{
                id:id
            },
            success:function(resp){
                if(resp.status == 0){
					$('#staff_'+id).remove();
                }
            }
        });
    },
	removeStaffByBatch:function(obj){
		var idArr = $(obj).closest('tr').find('input:checkbox[name="id"]:checked');
		if(idArr.length == 0){
			ui.msg.error('请选择要删除的人员');
			return;
		}
		var checkbox = null;
		var id = null;
		var ids = '';
		for(var i=0;i<idArr.length;i++){
			  checkbox = idArr[i];
			  id = checkbox.value;
			  ids += id + ",";
		}
		ids = ids.substring(0,ids.length - 1);
		Net.post({
			url:'/app_grid_privilege_delete',
			data:{
				ids:ids
			},
			success:function(resp){
				if(resp.status == 0){
					for(var i=0;i<idArr.length;i++){
						  checkbox = idArr[i];
						  id = checkbox.value;
						  $('#staff_'+id).remove();
					}
				}
			}
		});
	},
	bindRemoveUserEvent:function(){
		$('#newUsers').on('click','a',function(){
			var _this = $(this);
			var id = _this.prop('id');
			$('#user_'+id).remove();
			_this.closest('span').remove();
		});
	},
	bindStaffSelect2Event:function(){
		$("#name").select2({
			  minimumInputLength: 2,
			  placeholder: '请根据姓名/用户名进行搜索',
			  ajax: {
				  url: '/query_staff_list',
				  delay: 250,
				  data: function (params) {
				      return {
				        name: params.term
				      };
				  },
				  processResults: function (data) {
			            return {
			                results: $.map(data, function (item) {
			                    return {
			                        text: item.name,
			                        id: item.value
			                    }
			                })
			            };
			      }
			  }
		});
		$('#name').on('change',function(){
			AppGridPrivilege.addTargetStaff(this);
		});
	},
	addTargetStaff:function(obj){
		var option = $("option:selected", obj);
		var id = option.val();
		var name = option.text();
		if($('#user_'+id).length == 0){
			$('#inputForm').append('<input type="hidden" id="user_'+id+'" name="privilegeUserIds" value="'+id+'"/>');
			$('#newUsers').append('<span class="label label-info">'+name+' &nbsp;<a href="javascript:void(0)" id="'+id+'"><i class="fa fa-times text-red"></i></a></span> &nbsp;');
		}
	},
}
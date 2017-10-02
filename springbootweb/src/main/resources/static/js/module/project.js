Project = {
	add:function(){
		ui.box({
			title:'Project Add',
			remote:'/project_add',
			btn:'<button type="button" class="btn btn-success btn-sm" onclick="$(\'#inputForm\').submit()" id="checkedBtn">Submit</button>'
		});
	},
	edit:function(id){
		ui.box({
			title:'Project Edit',
			remote:'/project_update/'+id,
			btn:'<button type="button" class="btn btn-success btn-sm" onclick="$(\'#inputForm\').submit()" id="checkedBtn">Submit</button>'
		});
	},
	member:function(id){
		ui.box({
			title:'Project Member',
			size:'lg',
			remote:'/project_member/'+id
		});
	},
	addMember:function(){
		var members = $('#members').val();
		var role = $('#role').val();
		var projectId = $('#projectId').val();
		
		if( members == null ||  members.length == 0){
			ui.msg.warning('please choose people');
			return;
		}
		
		Net.post({
			url:'project_add_member',
			data:{
				projectId:projectId,
				members:members,
				role:role
			},
			boxClose:true
		})
	},
	showEditMember:function(id){
		var labelDiv = $('#member_mode_label_'+id);
		var optDiv = $('#member_mode_'+id);
		if(optDiv.hasClass('hide')){
			optDiv.removeClass('hide');
			labelDiv.addClass('hide');
		}else{
			optDiv.addClass('hide');
			labelDiv.removeClass('hide');
		}
	},
	editMember:function(id){
		var _this = this;
		var selected = $('#member_mode_'+id+' option:selected');
		var mode = selected.val();
		var modeLabel = selected.text();
		Net.post({
			url:'/project_edit_member/',
			data:{
				id:id,
				mode:mode
			},
			success:function(){
				_this.showEditMember(id);
				ui.msg.success( ui.msg.MSG_SUCCESS );
				$('#member_mode_label_'+id).text(modeLabel);
			}
		})
	},
	deleteMember:function(id){
		Net.post({
			url:'/project_delete_member/'+id,
			success:function(){
				$('#member_'+id).remove();
			}
		})
	},
	deleteById:function(id){
		ui.confirm('are you sure?',function(){
			Net.post({
				url:'/project_delete/'+id,
				go:'/project_list'
			})
		});
	}
}


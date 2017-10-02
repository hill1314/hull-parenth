var treeObj;
var setting = {
	view: {
		addHoverDom: addHoverDom,
		removeHoverDom: removeHoverDom,
		selectedMulti: false,
		nameIsHTML: true,
	},
    check: {
        enable: false
        ,chkStyle: 'radio'
        ,radioType: "level"
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    edit: {
        enable: true,
		showRemoveBtn: false,
		showRenameBtn: false
    },
    callback: {
        onClick: function(event, treeId, treeNode) {
        },
        beforeDrag: function(treeId, treeNodes) {
        	var status = $('#dragBtn').attr("status");
        	if(status == 1){
        		return true;
        	}else{
        		return false;
        	}
    	},
		beforeDrop:function(treeId, treeNodes, targetNode, moveType) {
			if(moveType != 'inner'){
				return false;
			}
			if(targetNode.type == 1){
				ui.msgWarning("leave node can't be folder");
				return false;
			}
			if(treeNodes[0].groupId != targetNode.groupId){
				ui.msgWarning("selected nodes and target node is NOT in the same group!");
				return false;
			}
		}
    }
};


function addHoverDom(treeId, treeNode) {
	var id = treeNode.id;
	var tId = treeNode.tId;
	var level = treeNode.level + 1;
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#menutButtonGroup_"+treeNode.tId).length>0) return;
    var addStr = [];
    addStr.push("<span id='menutButtonGroup_"+treeNode.tId+"'>");
    if(treeNode.type == 0){
    	addStr.push("<span class='button nodeBtn folder' onclick='SidebarTree.addFolder(\""+tId+"\")'></span> ");
    	addStr.push("<span class='button nodeBtn link'   onclick='SidebarTree.addLink(\""+tId+"\")'></span> ");
    	addStr.push("<span class='button nodeBtn edit'   onclick='SidebarTree.updateFolder(\""+tId+"\")'></span> ");
    	addStr.push("<span class='button nodeBtn remove'  onclick='Link.deleteFolder("+id+","+level+")'></span> ");
        addStr.push("<span class='button nodeBtn up'  onclick='Link.deleteFolder("+id+","+level+")'></span> ");
        addStr.push("<span class='button nodeBtn down'  onclick='Link.deleteFolder("+id+","+level+")'></span> ");
        addStr.push("</span>");
    }else{
    	addStr.push("<span class='button nodeBtn copy' onclick='Link.linkDuplicate("+id+")'></span> ");
    	addStr.push("<span class='button nodeBtn edit'   onclick='SidebarTree.updateLink(\""+tId+"\")'></span> ");
    	addStr.push("<span class='button nodeBtn remove'  onclick='SidebarTree.deleteLink("+id+")'></span> ");
        addStr.push("<span class='button nodeBtn up'  onclick='Link.deleteFolder("+id+","+level+")'></span> ");
        addStr.push("<span class='button nodeBtn down'  onclick='Link.deleteFolder("+id+","+level+")'></span> ");
        addStr.push("</span>");
    }
    sObj.after(addStr.join(''));
    
    
    //remove selected add on
    var nodes = treeObj.getSelectedNodes();
    if(nodes.length == 1){
    	var node = nodes[0];
    	if(node.id != id){
    		removeHoverDom(node.id, node);
    	}
    }
   
};

function removeHoverDom(treeId, treeNode) {
    $("#menutButtonGroup_"+treeNode.tId).unbind().remove();
};

function isNodeSelected(){
	var selected = false;
	var nodes = treeObj.getSelectedNodes();
	if(nodes.length == 1){
		selected = true;
		return selected;
	}else{
		ui.msg.warning( 'please click one node in the sidebar tree');
		return selected;
	}
}
function getCurrentNode(){
	var nodes = treeObj.getSelectedNodes();
	if(isNodeSelected()){
		return nodes[0];
	}
}
var zNodes =[];
var SidebarTree = {
	initTree:function(id){
		Net.get({
			   url: "/get_tree_nodes/"+projectId,
			   beforeSend:function(){
			   },
			   success: function(resp){
				   if(resp.status == 0 && resp.data.length > 0){
					   var dataList = resp.data;
					   var node = null;
					   for(var i=0;i<dataList.length;i++){
						   node = dataList[i];
						   node.title = node.name;
						   if(node.type == 1){
							   node.name = StrUtil.cutString(node.name,6);
							   if(node.method == 'POST'){
								   node.iconSkin= 'iconPOST';
							   }else{
								   node.iconSkin= 'iconGET';
							   }
							   node.isParent = 0;
						   }else{
							   node.name =  StrUtil.cutString(node.name);
							   node.isParent = 1;
						   }
						   if(id == node.id){
							   node.isOpen = 1;
						   }
						   zNodes.push(node);
					   }
					   
					   treeObj = $.fn.zTree.init($("#sidebarTreeUL"), setting, zNodes);
				   }else{
					   $('#addFolderBtn').removeClass('hide');
				   }
			   }
		});
	},
	addFolder:function(tId){
		var node = null;
		var pid = null;
		var level = null;
		if(tId != null && tId!=''){
			node = treeObj.getNodeByTId(tId);
		}
		if(node != null && node.type == 1){
			ui.msg.warning("Link node can't add folder");
			return;
		}
		if(node != null){
			pid = node.id;
			level = node.level;
		}else{
			level = 0;
		}
		
		ui.box({
			title:'New Folder',
			size:'xs',
			remote:'/tree_folder_add',
			param:{
				pid:pid,
				projectId:projectId,
				level:level
			},
			btn:'<button type="button" class="btn btn-success btn-sm" onclick="$(\'#inputForm\').submit()" id="checkedBtn">Submit</button>'
		});
	},
	updateFolder:function(tId){
		var node = treeObj.getNodeByTId(tId);
		var id = node.id;
		ui.box({
			title:'Update Folder',
			size:'xs',
			remote:'/tree_folder_update/'+id,
			btn:'<button type="button" class="btn btn-success btn-sm" onclick="$(\'#inputForm\').submit()" id="checkedBtn">Submit</button>'
		});
	},
	addLink:function(tId){
		var node = treeObj.getNodeByTId(tId);
		if(node != null){
			pid = node.id;
		}
		ui.box({
			title:'New Link',
			remote:'/tree_link_add',
			param:{
				pid:pid,
				projectId:projectId
			},
			btn:'<button type="button" class="btn btn-success btn-sm" onclick="$(\'#inputForm\').submit()" id="checkedBtn">Submit</button>'
		});
	},
	updateLink:function(tId){
		var node = treeObj.getNodeByTId(tId);
		var id = node.id;
		ui.box({
			title:'Update Link',
			remote:'/tree_link_update/'+id,
			btn:'<button type="button" class="btn btn-success btn-sm" onclick="$(\'#inputForm\').submit()" id="checkedBtn">Submit</button>'
		});
	},
	deleteLink:function(id){
		ui.confirm('Delete this link?',function(){
			Net.post({
				url:'/tree_delete_link/'+id,
				success:function(resp){
					if(resp.status == 0){
						var treeNode = treeObj.getNodeByParam("id", id);
						treeObj.removeNode(treeNode);
					}
				}
			});
		});
	},
	closeTree:function(){
		treeObj.expandAll(false);
	},
	initDraggable:function(btn){
		var btn = $(btn);
		var status = btn.attr("status");
		if(status == null || status == 0){
			btn.attr("status",1);
			btn.find('span').addClass("text-yellow");
			$('#dragInfoTip').removeClass('hide');
		}else{
			btn.attr("status",0);
			btn.find('span').removeClass("text-yellow");
			$('#dragInfoTip').addClass('hide');
		}
	},
	addNodeToZtree:function(node){
		node.pId = node.pid;
		if(node.type == 1){
			node.name = StrUtil.cutString(node.name,6);
			node.isParent = 0;
			if(node.method == 'POST'){
				node.iconSkin= 'iconPOST';
			}else{
				node.iconSkin= 'iconGET';
			}
		}else{
			node.name =  StrUtil.cutString(node.name);
			node.isParent = 1;
		}
		
		var treeNode = treeObj.getNodeByParam("id", node.id);
		if(treeNode == null){
			var pNode = treeObj.getNodeByParam("id", node.pId);
			treeObj.addNodes(pNode,node);
		}
	}
}

$(function(){
	SidebarTree.initTree();
})








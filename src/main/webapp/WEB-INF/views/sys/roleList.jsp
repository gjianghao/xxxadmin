<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%String path = request.getContextPath(); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色管理</title>
<script type="text/javascript" src="<%=path %>/static/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="<%=path %>/static/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" href="<%=path %>/static/easyui/themes/icon.css"></link>
<style type="">
 li {list-style-type:none;}
</style>
<script type="text/javascript">
	function doSearch(){
		$("#tt").datagrid('load', {
            "roleName": $("#roleName").textbox('getValue')
    	});
	}
	
	function addView(){
		$('#addWin').window('open');
	}
	function addSubmitForm(){
		$('#addff').form('submit', {
	        url:"<%=path%>/sysRole/saveRole",
	        onSubmit: function(){
	                var name = $("input[name='name']").val();
	                if(name == ""){
	                	$.messager.alert('提示', '角色名不能为空');
	                	return false;
	                }
	        },
	        success:function(data){
	                if(data == "success"){
	                	$.messager.alert('提示', '添加角色成功');
	                	addClearForm();
	                	$("#tt").datagrid('reload')
	                }else{
	                	$.messager.alert('提示', '添加角色失败');
	                }
	        }
		});
	}
	function addClearForm(){
		$('#addff').form('clear');
		$('#addWin').window('close');
	}
	
	function updateRole(){
		var rows = $('#tt').datagrid('getSelections');
		if(rows == null || rows == "" || rows == undefined){
			$.messager.alert('提示', '没有选中任何记录，不能修改');
			return;
		}
		var row = rows[0];
		$("#update_id").val(row.id);
		$("#update_name").textbox('setValue',row.name);
		$("#update_description").textbox('setValue',row.description);
		$('#updateWin').window('open');
	}
	function updateCancle(){
		$('#updateWin').window('close');
	}
	function updateRoleSubmit(){
		$('#updateff').form('submit', {
	        url:"<%=path%>/sysRole/updateRole",
	        onSubmit: function(){
	                var name = $("#update_name").textbox('getValue');
	                if(name == ""){
	                	$.messager.alert('提示', '角色名不能为空');
	                	return false;
	                }
	        },
	        success:function(data){
	                if(data == "success"){
	                	$.messager.alert('提示', '修改角色成功');
	                	$('#updateWin').window('close');
	                	$("#tt").datagrid('reload')
	                }else{
	                	$.messager.alert('提示', '修改角色失败');
	                }
	        }
		});
	}
	
	
	function deleteRole(){
		var rows = $('#tt').datagrid('getSelections');
		if(rows == null || rows == "" || rows == undefined){
			$.messager.alert('提示', '没有选中任何记录，不能删除');
			return;
		}
		var id = rows[0].id;
		$.messager.confirm('提示', '您确定要删除该条记录吗?', function(r){
			if (r){
				$.ajax({
					   type: "POST",
					   url: "<%=path%>/sysRole/deleteRole",
					   data: {'id':id},
					   success: function(data){
						   if(data == "success"){
							   $.messager.alert('提示', '删除成功');
							   $("#tt").datagrid('reload')
						   }else{
							   $.messager.alert('提示', '删除失败');
						   }
					   }
					});
			}
		});
	}
	
	function resourceView(id){
		$("#hidden_role_id").val(id);
		$('#treett').tree({  
		    url:"<%=path%>/sysRole/getResources?roleId="+id    // The url will be mapped to NodeController class and getNodes method  
		});
		$('#resourceWin').window('open');
	}
	function resourceCancle(){
		$('#resourceWin').window('close');
	}
	function resourceSubmit(){
		var resources = "";
		var nodes = $('#treett').tree('getChecked');
		for(var i=0; i<nodes.length; i++){
			if (resources != '') resources += ',';
			resources += nodes[i].id;
		}
		$.ajax({
			   type: "POST",
			   url: "<%=path%>/sysRole/assignResources",
			   data: {'resources':resources.split(","),'roleId':$("#hidden_role_id").val()},
			   success: function(data){
				   if(data == "success"){
					   $.messager.alert('提示', '分配菜单成功');
					   resourceCancle();
				   }else{
					   $.messager.alert('提示', '分配菜单失败');
				   }
			   }
			});
	}
	
	
	function formatResource(val,row){
		return '<span style=""><a style="text-decoration:none" href="javascript:void(0)" onclick="resourceView('+row.id+')" >资源</a></span>';
	}

</script>
</head>
<body>
	<table id="tt" class="easyui-datagrid" title="角色管理" pagination="true" fit="true" style="width:700px;height:250px"
			data-options="rownumbers:true,singleSelect:true,url:'<%=path %>/sysRole/datagrid',method:'post',toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'id',width:100,align:'center',hidden:'true'">ID</th>
				<th data-options="field:'name',width:100,align:'center'">角色名称</th>
				<th data-options="field:'description',width:150,align:'center'">角色描述</th>
				<th data-options="field:'分配资源',width:80,align:'center'" formatter="formatResource">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:void(0)" onclick="addView()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
			<a href="javascript:void(0)" onclick="updateRole()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
			<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a> -->
			<a href="javascript:void(0)" onclick="deleteRole()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		</div>
		<div>
			角色名称: <input class="easyui-textbox" type="text" id="roleName"/> <!-- data-options="required:true" -->
			<!-- Date From: <input class="easyui-datebox" style="width:80px">
			To: <input class="easyui-datebox" style="width:80px"> -->
			<!-- Language: 
			<select class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="java">Java</option>
				<option value="c">C</option>
				<option value="basic">Basic</option>
				<option value="perl">Perl</option>
				<option value="python">Python</option>
			</select> -->
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doSearch()" iconCls="icon-search">Search</a>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window" title="添加角色" data-options="iconCls:'icon-save',closed:'true'" style="width:320px;height:220px;padding:5px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">
			<form id="addff" method="post">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>角色名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>角色描述:</td>
	    			<td><input class="easyui-textbox" type="text" name="description" data-options=""></input></td>
	    		</tr>
	    	</table>
	    </form>
			</div>
			<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:addSubmitForm()" style="width:80px">Ok</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:addClearForm()" style="width:80px">Cancel</a>
			</div>
		</div>
	</div>
	
	<div id="updateWin" class="easyui-window" title="修改角色" data-options="iconCls:'icon-save',closed:'true'" style="width:320px;height:220px;padding:5px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">
			<form id="updateff" method="post">
			<input type="hidden" name="id" id="update_id"/>
	    	<table cellpadding="5">
	    		<tr>
	    			<td>角色名称:</td>
	    			<td><input class="easyui-textbox" type="text" id="update_name" name="name" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>角色描述:</td>
	    			<td><input class="easyui-textbox" type="text" id="update_description" name="description" data-options=""></input></td>
	    		</tr>
	    	</table>
	    </form>
			</div>
			<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:updateRoleSubmit()" style="width:80px">Ok</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:updateCancle()" style="width:80px">Cancel</a>
			</div>
		</div>
	</div>
	
	<div id="resourceWin" class="easyui-window" title="菜单分配" data-options="iconCls:'icon-save',closed:'true'" style="width:400px;height:300px;padding:5px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">
				<input id="hidden_role_id" type="hidden"/>
						<ul id="treett" class="easyui-tree" checkbox="true">
						</ul>
			</div>
			<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:resourceSubmit()" style="width:80px">Ok</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:resourceCancle()" style="width:80px">Cancel</a>
			</div>
		</div>
	</div>
</body>
</html>
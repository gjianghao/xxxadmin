<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"  isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%String path = request.getContextPath(); %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
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
            "username": $("#username").val(),
            "realName":$("#realName").val()
    	});
	}
	
	function addView(){
		$('#addWin').window('open');
	}
	function addSubmitForm(){
		$('#addff').form('submit', {
	        url:"<%=path%>/sysUser/saveUser",
	        onSubmit: function(){
	                var addUname = $("input[name='addUname']").val();
	                var addRname = $("input[name='addRname']").val();
	                var addPwd = $("input[name='addPwd']").val();
	                if(addUname == ""){
	                	$.messager.alert('提示', '用户名不能为空');
	                	return false;
	                }
	                if(addRname == ""){
	                	$.messager.alert('提示', '姓名不能为空');
	                	return false;
	                }
	                if(addPwd == ""){
	                	$.messager.alert('提示', '密码不能为空');
	                	return false;
	                }
	        },
	        success:function(data){
	                if(data == "success"){
	                	$.messager.alert('提示', '添加用户成功');
	                	addClearForm();
	                	$("#tt").datagrid('reload')
	                }else{
	                	$.messager.alert('提示', '添加用户失败');
	                }
	        }
		});
	}
	function addClearForm(){
		$('#addff').form('clear');
		$('#addWin').window('close');
	}
	
	
	function deleteUser(){
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
					   url: "<%=path%>/sysUser/deleteUser",
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
	
	function roleView(id){
		$("#hidden_user_id").val(id);
		$.ajax({
			   type: "POST",
			   url: "<%=path%>/sysUser/getRoles",
			   data: {'userId':id},
			   success: function(data){
				   var allRoleList = data.data1;
				   var selfRoleList = data.data2;
				   var html = "";
				   for(var i = 0; i < allRoleList.length; i++){
					   var flag = "0"
					   for(var j = 0; j < selfRoleList.length; j++){
						   if(allRoleList[i].id == selfRoleList[j].id){
								flag = "1";
								continue;
						   }
					   }
					   if(flag == "0"){
						   html += "<li> <input type='checkbox' name='roleId' value='"+allRoleList[i].id+"'   />"+allRoleList[i].name+"</li>"
					   }else{
						   html += "<li> <input type='checkbox' checked name='roleId' value='"+allRoleList[i].id+"'   />"+allRoleList[i].name+"</li>"
					   }
				   }
				   $("#roles").html(html);
				   $('#roleWin').window('open');
			   }
			});
	}
	function roleCancle(){
		$('#roleWin').window('close');
	}
	function roleSubmit(){
		var roles = "";
		$('input[name="roleId"]:checked').each(function(){ 
            roles += $(this).val()+",";
        });
		if(roles.length > 0){
			roles = roles.substring(0, roles.length-1)
		}
		$.ajax({
			   type: "POST",
			   url: "<%=path%>/sysUser/assignRoles",
			   data: {'roles':roles.split(","),'userId':$("#hidden_user_id").val()},
			   success: function(data){
				   if(data == "success"){
					   $.messager.alert('提示', '分配角色成功');
					   roleCancle();
				   }else{
					   $.messager.alert('提示', '分配角色失败');
				   }
			   }
			});
		
	}
	function formatRole(val,row){
		return '<span style=""><a style="text-decoration:none" href="javascript:void(0)" onclick="roleView('+row.id+')" >赋权</a></span>';
	}
	
	function formatDate(value,row,index){  
		var unixTimestamp = new Date(value);  
		return unixTimestamp.toLocaleString();  
	}  

</script>
</head>
<body>
	<table id="tt" class="easyui-datagrid" title="用户管理" pagination="true" fit="true" style="width:700px;height:250px"
			data-options="rownumbers:true,singleSelect:true,url:'<%=path %>/sysUser/datagrid',method:'post',toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'id',width:100,align:'center',hidden:'true'">ID</th>
				<th data-options="field:'username',width:100,align:'center'">用户名</th>
				<th data-options="field:'realName',width:100,align:'center'">姓名</th>
				<th data-options="field:'createTime',width:150,align:'center'" formatter="formatDate">添加时间</th>
				<th data-options="field:'操作',width:80,align:'center'" formatter="formatRole">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:void(0)" onclick="addView()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
			<!-- <a href="javascript:void(0)" onclick="updateUser()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a> -->
			<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a> -->
			<a href="javascript:void(0)" onclick="deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		</div>
		<div>
			用户名: <input class="easyui-textbox" type="text" id="username"/> <!-- data-options="required:true" -->
			姓名: <input class="easyui-textbox" type="text" id="realName"/>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doSearch()" iconCls="icon-search">Search</a>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window" title="添加用户" data-options="iconCls:'icon-save',closed:'true'" style="width:320px;height:220px;padding:5px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">
			<form id="addff" method="post">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>姓名:</td>
	    			<td><input class="easyui-textbox" type="text" name="realName" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>用户名:</td>
	    			<td><input class="easyui-textbox" type="text" name="username" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>密码:</td>
	    			<td><input class="easyui-textbox" type="text" name="password" data-options="required:true"></input></td>
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
	
	<div id="roleWin" class="easyui-window" title="角色分配" data-options="iconCls:'icon-save',closed:'true'" style="width:400px;height:300px;padding:5px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">
				<input id="hidden_user_id" type="hidden"/>
				<ul id="roles">
				</ul>
			</div>
			<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:roleSubmit()" style="width:80px">Ok</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:roleCancle()" style="width:80px">Cancel</a>
			</div>
		</div>
	</div>
</body>
</html>
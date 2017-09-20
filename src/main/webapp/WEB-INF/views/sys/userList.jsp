<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%String path = request.getContextPath(); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<script type="text/javascript" src="<%=path %>/static/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="<%=path %>/static/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" href="<%=path %>/static/easyui/themes/icon.css"></link>
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
	
	function getSelections(){
		var ss = [];
		var rows = $('#dg').datagrid('getSelections');
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			ss.push('<span>'+row.itemid+":"+row.productid+":"+row.attr1+'</span>');
		}
		$.messager.alert('Info', ss.join('<br/>'));
	}
</script>



</head>



<body>
	<table id="tt" class="easyui-datagrid" title="用户管理" pagination="true" fit="true" style="width:700px;height:250px"
			data-options="rownumbers:true,singleSelect:true,url:'<%=path %>/sysUser/datagrid',method:'get',toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'username',width:100,align:'center'">用户名</th>
				<th data-options="field:'realName',width:100,align:'center'">姓名</th>
				<th data-options="field:'createTime',width:120,align:'center'">添加时间</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:void(0)" onclick="addView()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
			<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a> -->
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		</div>
		<div>
			用户名: <input class="easyui-textbox" type="text" id="username"/> <!-- data-options="required:true" -->
			姓名: <input class="easyui-textbox" type="text" id="realName"/>
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
	
	<div id="addWin" class="easyui-window" title="Window Layout" data-options="iconCls:'icon-save',closed='true'" style="width:500px;height:200px;padding:5px;">
		
	</div>
</body>
</html>
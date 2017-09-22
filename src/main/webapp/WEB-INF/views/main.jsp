<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%String path = request.getContextPath(); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理后台</title>
<script type="text/javascript" src="<%=path %>/static/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="<%=path %>/static/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" href="<%=path %>/static/easyui/themes/icon.css"></link>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div data-options="region:'north'" style="height:50px">
			<table width="100%">
		        <tr>
		            <td width="20%"></td>
		            <td valign="bottom"
		                style="font-size: 20px;color:#8B8B8B;font-family: '楷体';"
		                align="right" width="70%"><font size="3">&nbsp;&nbsp;<strong>您好：</strong>
		                ${username}</font>
		            </td>
		            <td valign="bottom"
		                style="font-size: 20px;color:#8B8B8B;font-family: '楷体';"
		                align="left" width="10%">
		                <a style="margin-left: 30px; text-decoration:none;" href="<%=path %>/logout">注销</a>
		            </td>
		        </tr>
	    	</table>
		</div>
		<div data-options="region:'south',split:true" style="height:50px;"></div>
		<!-- <div data-options="region:'east',split:true" title="East" style="width:180px;">
			<ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true,dnd:true"></ul>
		</div> -->
		<div data-options="region:'west',split:true" title="导航" style="width:150px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<c:forEach items="${listParentResource }" var="pr">
					<div iconCls="icon-tree-p" title="${pr.name }" data-options="selected:false"  style="padding:10px;">
						<c:forEach items="${listChildResource }" var="cr">
							<c:if test="${pr.id == cr.parentId}"></c:if>
							<a iconCls="icon-tree-c" href="javascript:openTab('${cr.name }','<%=path %>/${cr.href}','icon-shujia')"
			                    class="easyui-linkbutton"
			                    data-options="plain:true,iconCls:''"
			                    style="width: 120px;">${cr.name }</a>
						</c:forEach>
					</div>	
				</c:forEach>
			</div>
		</div>
		<div id="main_layout" data-options="region:'center',title:'商品分类',iconCls:'icon-ok'">
			<div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tabs">
				<div title="首页" data-options="iconCls:'icon-home'">
		            
		            <div align="center" style="padding-top: 50px">
		                <font color="grey" size="10">xxxadmin</font>
		            </div>
		        </div>
			</div>
		</div>
	</div>
<script type="text/javascript">

var url;
function addTab(url, text, iconCls) {
    var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='"+ url + "'></iframe>";
    $("#tabs").tabs("add", {
        title: text,
        iconCls: iconCls,
        closable: true,
        content: content
    });
}
function openTab(text, url, iconCls) {
    if ($("#tabs").tabs("exists", text)) {
        $("#tabs").tabs("close", text);
        addTab(url, text, iconCls);
        $("#tabs").tabs("select", text);
    } else {
        addTab(url, text, iconCls);
    }
}

</script>
</body>  
</html>
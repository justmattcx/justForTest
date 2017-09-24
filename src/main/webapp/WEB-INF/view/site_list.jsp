<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mattcx.t4bn.common.Common" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>站點列表</title>	
<link href="${contextPath}/vendor/bootstrap/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet" media="screen">
<!-- Custom styles for this template -->
<link href="${contextPath}/css/sitelist.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-header" >
		<a href="${contextPath}/main" class="btn navbar-btn pull-left" role="button">
			<span class="glyphicon glyphicon-chevron-left"></span>
		</a>
		<a href="#" class="navbar-brand" >站點列表</a>
	</div>
</nav>

<!-- start: container -->
<div class="container">
<div class="sitelist-container">
<table class="table table-hover">
<thead><tr>
	<th>站點</th>
	<th>修改時間</th>
	<th>動作</th>
</tr></thead>
<tbody>
	
 	<c:forEach var="site" items="${siteList}">
		<tr class="text-info">
			<td>${site.siteName} </td>
			<td>${Common.timestampFormat(site.updDatetime,"yyyy/MM/dd HH:mm:ss")}</td>
			<td>
				<a href="${contextPath}/site/edit/${site.siteId}">View</a> / 
				<a href="${contextPath}/site/doDel/${site.siteId}">Del</a>
			</td>
		</tr>
	</c:forEach>	
		
</tbody>
</table>
</div></div>
<!-- end: container -->
   
<script src="${contextPath}/vendor/jquery/jquery-3.2.1/jquery-3.2.1.js"></script>
<script src="${contextPath}/vendor/bootstrap/bootstrap-3.3.7/js/bootstrap.js"></script>
</body>
</html>
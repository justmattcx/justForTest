<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>main</title>	
<link href="${contextPath}/vendor/bootstrap/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet" media="screen">
<!-- Custom styles for this template -->
<link href="${contextPath}/css/main.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">華廣測驗 (陳宇韜)</a>
		</div>
	</div>
</nav>

<!-- end: container -->
<div class="container"><div class="common-container">
	<div class="list-group">
		<a href="site/add" class="list-group-item" role="button">新增站點</a>
		<a href="site" class="list-group-item" role="button">站點列表</a>
		<a href="nurse/add" class="list-group-item" role="button">新增護士</a>
		<a href="nurse" class="list-group-item" role="button">護士列表</a>
	</div>
</div></div>
<!-- end: container -->
   
<script src="${contextPath}/vendor/jquery/jquery-3.2.1/jquery-3.2.1.js"></script>
<script src="${contextPath}/vendor/bootstrap/bootstrap-3.3.7/js/bootstrap.js"></script>
</body>
</html>
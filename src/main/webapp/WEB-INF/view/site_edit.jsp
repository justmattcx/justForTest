<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>修改站點資料</title>	
	
<link href="../../vendor/bootstrap/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet" media="screen">
<!-- Custom styles for this template -->
<link href="../../css/siteedit.css" rel="stylesheet">

</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-header" >			
		<a href="../../site" class="btn navbar-btn pull-left" role="button">
			<span class="glyphicon glyphicon-chevron-left"></span>
		</a>
		<a href="#" class="navbar-brand" >修改站點資料</a>
	</div>	
</nav>

<!-- end: container -->
<div class="container"><div class="common-container">

	<form action="../doEdit" method="post" class="form-horizontal">
		<input type=hidden id="siteId" name="siteId" value="${siteId}" >
		<div class="form-group">
			<label for="inputSiteName" class="col-sm-4 control-label">站點名稱</label>
			<div class="col-sm-6">
				<input type=text class="form-control" 
					id="siteName" name="siteName" placeholder="請輸入站點名稱" value="${site.siteName}">
			</div> 
		</div>
  
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-10">
      			<button type="submit" class="btn btn-default">確定修改</button>
    		</div>
  		</div>
	</form>

</div></div>
<!-- end: container -->
   
<script src="../../vendor/jquery/jquery-3.2.1/jquery-3.2.1.js"></script>
<script src="../../vendor/bootstrap/bootstrap-3.3.7/js/bootstrap.js"></script>

</body>
</html>
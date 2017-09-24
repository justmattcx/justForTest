<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>新增站點</title>	
	
<link href="../vendor/bootstrap/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet" media="screen">
<!-- Custom styles for this template -->
<link href="../css/sitenew.css" rel="stylesheet">

</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-header" >		
		<a href="../main" class="btn navbar-btn pull-left" role="button">
			<span class="glyphicon glyphicon-chevron-left"></span>
		</a>	
		<a href="#" class="navbar-brand" >新增站點</a>
	</div>
</nav>

<!-- start: container -->
<div class="container"><div class="commom-container">

	<form action="./doAdd" method="post" class="form-horizontal" >
		<div class="form-group">
			<label for="inputSiteName" class="col-sm-4 control-label">站點名稱</label>
			<div class="col-sm-6">
				<input type=text class="form-control" id="siteName" name="siteName" placeholder="請輸入站點名稱">
			</div> 
		</div>
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-10">
      			<button type="submit" class="btn btn-default">確定新增</button>
    		</div>
  		</div>
	</form>

</div></div>
<!-- end: container -->
   
<script src="../vendor/jquery/jquery-3.2.1/jquery-3.2.1.js"></script>
<script src="../vendor/bootstrap/bootstrap-3.3.7/js/bootstrap.js"></script>
</body>
</html>
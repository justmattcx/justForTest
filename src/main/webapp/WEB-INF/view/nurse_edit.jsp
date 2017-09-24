<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>修改護士資料</title>	
<link href="${contextPath}/vendor/bootstrap/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet" media="screen">
<!-- Custom styles for this template -->
<link href="${contextPath}/css/nurseedit.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-header" >
		<a href="../../nurse" class="btn navbar-btn pull-left" role="button">
			<span class="glyphicon glyphicon-chevron-left"></span>
		</a>	
		<a href="#" class="navbar-brand" >修改護士資料</a>
	</div>	
</nav>

<!-- start: container -->
<div class="container"><div class="common-container">
	<form action="${contextPath}/nurse/doEdit" method="post" 
		class="form-horizontal" data-toggle="validator" >
		<input type=hidden id="nurseId" name="nurseId" value="${nurseId}" >
		<div class="form-group">
			<label for="inputNurseNo" class="col-sm-2 control-label">員工編號</label>
			<div class="col-sm-6">
				<input type=text class="form-control" value="${nurse.nurseNo}" id="nurseNo" name="nurseNo" 
					placeholder="請輸入員工編號" autocomplete="off" maxlength="20" required>
			</div>
			<div class="help-block with-errors"></div>
		</div>
 		<div class="form-group">
			<label for="inputNurseName" class="col-sm-2 control-label">護士姓名</label>
			<div class="col-sm-6">
				<input type=text class="form-control" value="${nurse.nurseName}" id="nurseName" name="nurseName" 
					placeholder="請輸入護士姓名" autocomplete="off" maxlength="20" required>
			</div>
			<div class="help-block with-errors"></div>
		</div>
		 
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-1">
      			<button type="submit" class="btn btn-primary" id="doSaveBtn">儲存修改</button>
    		</div>
  		</div>  		  		
  		<hr/>
  		<div class="form-group">
			
			<div class="col-xs-4">
				分配站點
				<select name="to" id="undo_redo_to" class="form-control" size="8" multiple="multiple">
					<c:forEach var="nurseSite" items="${nurseSiteList}">
						<option value="${nurseSite.siteId}">${nurseSite.siteName}</option></c:forEach>	
				</select>	
			</div>
				
			<div class="col-xs-2">
				<br/>
				<button type="button" id="undo_redo_rightSelected" class="btn btn-default btn-block">
					加入<i class="glyphicon glyphicon-chevron-left"></i>
				</button>
				<button type="button" id="undo_redo_leftSelected" class="btn btn-default btn-block">
					移除<i class="glyphicon glyphicon-chevron-right"></i>
				</button>
			</div>
				
			<div class="col-xs-4">
				可選站點
				<select name="from" id="undo_redo" class="form-control" size="8" multiple="multiple">
					<c:forEach var="site" items="${siteList}">
						<option value="${site.siteId}">${site.siteName}</option>
					</c:forEach>
				</select>
			</div> 			
			
  		</div>
	</form>

</div></div>
<!-- end: container -->
   
<script src="${contextPath}/vendor/jquery/jquery-3.2.1/jquery-3.2.1.js"></script>
<script src="${contextPath}/vendor/bootstrap/bootstrap-3.3.7/js/bootstrap.js"></script>
<script src="${contextPath}/vendor/bootstrap/bootstrap-validator-0.11.9/validator.js"></script>
<script src="${contextPath}/vendor/jquery/multiselect-2.0.0/multiselect.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		$('#undo_redo').multiselect();
		$('#doSaveBtn').click(function() {
		    $('#undo_redo_to option').prop('selected', true);
		});				
	});
	
</script>
</body>
</html>
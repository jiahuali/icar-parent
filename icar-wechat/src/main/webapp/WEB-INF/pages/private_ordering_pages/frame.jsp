<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html ng-app='privateOrderingApp'>

<head>
<meta charset="UTF-8">
<title>长安汽车</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link href="../css/mui.min.css" rel="stylesheet" />
</head>

<body>
	<header class="mui-bar mui-bar-nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"
			href="#/"></a>
		<h1 class="mui-title">what fuck2</h1>
	</header>
	<div ng-view></div>
	<script src="../js/mui.min.js"></script>
	<script
		src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
	<script
		src="http://apps.bdimg.com/libs/angular-route/1.3.13/angular-route.js"></script>
	<script>
		var openId = "${openId}";
	</script>
	<script src="../angular/private_ordering/privateOrderingApp.js"></script>
	<script src="../angular/private_ordering/homeCtr.js"></script>
	<script src="../angular/private_ordering/newIdeaCtr.js"></script>
</body>

</html>
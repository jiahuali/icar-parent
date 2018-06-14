<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html>

<head>
<meta charset="UTF-8">
<title>车辆位置</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link href="../css/mui.min.css" rel="stylesheet" />
</head>
<style type="text/css">
body, html, #container {
	height: 100%;
	margin: 0px;
}
</style>

<body ng-app="deviceLocationApp">

	<div id="container" tabindex="0"
		ng-controller="deviceLocationController"></div>

	<script type="text/javascript"
		src="http://webapi.amap.com/maps?v=1.4.0&key=cde5fe1389abe8d1cd0e11380ed5ee3a"></script>
	<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
	<script
		src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
	<script src="../js/urlConfig.js"></script>
	<script src="../angular/deviceLocationApp.js"></script>
	<script type="text/javascript">
		var openId = "${openId}";
	</script>
	<script src="../angular/deviceLocationController.js"></script>
	<script src="../js/mui.min.js"></script>

</body>

</html>
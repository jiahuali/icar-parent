<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html ng-app="wyjrConfirmApp">

<head>
<meta charset="UTF-8">
<title>好友位置</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link href="../css/mui.min.css" rel="stylesheet" />
</head>
<style type="text/css">
body, html, #container {
	height: 100%;
	margin: 0px;
}

#sendBtn {
	position: absolute;
	z-index: 9999;
	bottom: 50px;
	right: 80px;
	width: 200px;
}
</style>

<body ng-controller="wyjrConfirmController">

	<div id="container" tabindex="0">
	</div>
	<button type="button" class="mui-btn mui-btn-blue mui-btn-block"
		id="sendBtn" ng-click="sendClick()" data-loading-text="加载中">发送给设备</button>

	<script type="text/javascript"
		src="http://webapi.amap.com/maps?v=1.4.0&key=cde5fe1389abe8d1cd0e11380ed5ee3a"></script>
	<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
	<script
		src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
	<script src="../js/urlConfig.js"></script>
	<script src="../js/mui.min.js"></script>
	<script type="text/javascript">
		var openId = "${openId }";
		var pickCode="${pickCode }";
	</script>
	<script src="../angular/wyjrConfirmApp.js"></script>
	<script src="../angular/wyjrConfirmController.js"></script>
</body>

</html>
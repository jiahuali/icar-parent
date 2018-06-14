<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="zrjwConfirmApp">

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

#btnRow {
	position: absolute;
	z-index: 9999;
	bottom: 50px;
	right: 80px;
	width: 200px;
}
</style>

<body ng-controller="zrjwConfirmController">

	<div id="container" tabindex="0"></div>
	<div class="mui-button-row" id="btnRow">
		<button type="button" class="mui-btn mui-btn-blue mui-btn-block"
			id="sendBtn" ng-click="sendClick()" data-loading-text="加载中">帮助好友</button>
		<button type="button" class="mui-btn mui-btn-danger mui-btn-block"
			id="rejectBtn" ng-click="rejectClick()">拒绝好友</button>
	</div>

	<script type="text/javascript"
		src="http://webapi.amap.com/maps?v=1.4.0&key=cde5fe1389abe8d1cd0e11380ed5ee3a"></script>
	<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
	<script
		src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
	<script src="../js/urlConfig.js"></script>
	<script src="../js/mui.min.js"></script>
	<script type="application/javascript">
		
			var openId = "${openId}";
			var pickCode = "${pickCode}";
			var nickName = "${nickName}";
			var headImgUrl = "${headImgUrl}";
		
	</script>
	<script src="../angular/zrjwConfirmApp.js"></script>
	<script src="../angular/zrjwConfirmController.js"></script>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html ng-app="zrjwSelectLocationApp">

	<head>
		<meta charset="UTF-8">
		<title>找人接我</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="../css/mui.min.css" rel="stylesheet" />

	</head>
	<style type="text/css">
		body,
		html,
		#container {
			width: 100%;
			height: 100%;
			margin: 0px;
			font-size: 13px;
		}
		
		#pickerBox {
			position: absolute;
			z-index: 9999;
			top: 50px;
			right: 30px;
			width: 300px;
		}
		
		#pickerInput {
			width: 1005;
			padding: 5px 5px;
			background-color: grey;
			color: white;
		}
		
		#sendBtn {
			position: absolute;
			z-index: 9999;
			bottom: 50px;
			right: 80px;
			width: 200px;
		}
	</style>

	<body ng-controller="zrjwSelectLocationController">
		<div id="pickerBox">
			<input type="search" class="mui-input-clear" id="pickerInput" placeholder="手动输入地址">
			<div id="poiInfo"></div>
		</div>
		<div id="container" tabindex="0">

		</div>

		<button type="button" class="mui-btn mui-btn-blue mui-btn-block" id="sendBtn" ng-click="pickMeClick()" data-loading-text="定位中">快来接我</button>

		<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.0&key=cde5fe1389abe8d1cd0e11380ed5ee3a"></script>
		<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
		<script src="../js/mui.min.js"></script>
		<script src="../js/urlConfig.js"></script>
		<script>
			var openId = "${openId}";
			var nickName = "${nickName }";
			var headImgUrl = "${headImgUrl}";
		</script>
		<script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
		<script src="../angular/zrjwSelectLocationApp.js"></script>
		<script src="../angular/zrjwSelectLocationController.js"></script>
		<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	</body>

</html>
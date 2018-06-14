<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html ng-app="deviceManageApp">

<head>
<meta charset="UTF-8">
<title>设备管理</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link href="../css/mui.min.css" rel="stylesheet" />

</head>

<body>
	<!--
        	作者：578525306@qq.com
        	时间：2017-10-10
        	描述：angularjs管理
        -->
	<header class="mui-bar mui-bar-nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">设备管理</h1>
	</header>
	<br />
	<div class="mui-content">

		<div ng-controller="deviceController">

			<button type="button" class="mui-btn mui-btn-blue mui-btn-block"
				ng-click="addDevice()">添加设备</button>

			<h3>设备列表</h3>
			<br />
			<ul class="mui-table-view">
				<li class="mui-table-view-cell mui-collapse"
					ng-repeat="device in deviceList"><a class="mui-navigate-right"
					href="#">{{device.name}}</a><span
					ng-if="device.tuid==defaultDevice" style="color: chartreuse;">默认设备</span>
					<div class="mui-collapse-content">
						<div class="mui-input-row">
							<label>设备序列</label> <input type="text" style="font-size: small;"
								class="mui-input-clear" disabled="disabled"
								value="{{device.tuid}}">
						</div>

						<div class="mui-input-row">
							<label>设备型号</label> <input type="text" class="mui-input-clear"
								disabled="disabled" ng-model="device.model">
						</div>

						<div class="mui-input-row">
							<label>设备名称</label> <input type="text" class="mui-input-clear"
								disabled="disabled" ng-model="device.name">
						</div>

						<div class="mui-button-row">
							<button type="button" class="mui-btn mui-btn-primary"
								ng-click="changeName($index)">修改设备名</button>
							<button type="button" class="mui-btn mui-btn-primary"
								ng-click="setAsDefault($index)">设为默认设备</button>
							<button type="button" class="mui-btn mui-btn-danger"
								ng-click="deleteDevice($index)">取消绑定</button>
						</div>
					</div>
		</div>
		</li>
		</ul>
	</div>
	<script
		src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
		<script type="text/javascript">
			var openId = "${openId}";
		</script>
	<script src="../js/mui.min.js"></script>
	<script src="../js/urlConfig.js"></script>
	<script src="../angular/deviceManageApp.js"></script>
	<script src="../angular/deviceController.js"></script>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html ng-app="privateOrderingApp">

<head>
<meta charset="UTF-8">
<title>长安汽车</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link href="../css/mui.min.css" rel="stylesheet" />

</head>

<body ng-controller="privateOrderingController">
	<div class="mui-content">
		<div class="mui-slider">
			<div class="mui-slider-group mui-slider-loop">
				<!--支持循环，需要重复图片节点-->
				<div class="mui-slider-item mui-slider-item-duplicate">
					<a href="#"><img src="../img/4.jpg" /></a>
				</div>
				<div class="mui-slider-item">
					<a href="#"><img src="../img/1.jpg" /></a>
				</div>
				<div class="mui-slider-item">
					<a href="#"><img src="../img/2.jpg" /></a>
				</div>
				<div class="mui-slider-item">
					<a href="#"><img src="../img/3.jpg" /></a>
				</div>
				<div class="mui-slider-item">
					<a href="#"><img src="../img/4.jpg" /></a>
				</div>
				<div class="mui-slider-item mui-slider-item-duplicate">
					<a href="#"> <img src="../img/1.jpg">
					</a>
				</div>
			</div>
		</div>
		<!--end of slider-->

		<!--触发字符：mgrid-->
		<ul class="mui-table-view mui-grid-view mui-grid-9">
			<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"
				ng-click="newIdeaBtn()"><a href="#"> <span
					class="mui-icon mui-icon mui-icon-upload"></span>
					<div class="mui-media-body">创意提交</div>
			</a></li>
			<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"
				ng-click="complainBtn()"><a href="#/"> <span
					class="mui-icon mui-icon-upload"></span>
					<div class="mui-media-body">痛点抱怨</div>
			</a></li>
			<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"
				ng-click="queryIdeaBtn()"><a href="#/"> <span
					class="mui-icon mui-icon-search"></span>
					<div class="mui-media-body">进度查询</div>
			</a></li>
			<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"
				ng-click="funcMarketBtn()"><a href="#/"> <span
					class="mui-icon mui-icon-star"></span>
					<div class="mui-media-body">功能商城</div>
			</a></li>
			<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"
				ng-click="installedFucBtn()"><a href="#/"> <span
					class="mui-icon mui-icon-checkmarkempty"></span>
					<div class="mui-media-body">已安装功能</div>
			</a></li>
			<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"
				ng-click="avaliableFuncBtn()"><a href="#/"> <span
					class="mui-icon mui-icon-download"></span>
					<div class="mui-media-body">可安装功能</div>
			</a></li>
			<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"
				ng-click="ideaBBSBtn()"><a href="#/"> <span
					class="mui-icon mui-icon mui-icon-chatboxes"></span>
					<div class="mui-media-body">论坛</div>
			</a></li>
			<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"
				ng-click="infoBtn()"><a href="#/"> <span
					class="mui-icon mui-icon-help"></span>
					<div class="mui-media-body">使用说明</div>
			</a></li>
			
			<li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"
				ng-click="rewardBtn()"><a href="#/"> <span
					class="mui-icon mui-icon-plusempty"></span>
					<div class="mui-media-body">奖励领取</div>
			</a></li>
		</ul>
	</div>
	<script
		src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
	<script src="../js/mui.min.js"></script>
	<script src="../js/urlConfig.js"></script>
	<script type="text/javascript">
		var openId = "${openId}";
	</script>
	<script src="../angular/privateOrderingApp.js"></script>
	<script src="../angular/privateOrderingController.js"></script>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>

<head>
<meta charset="UTF-8">
<title>我要接人</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link href="../css/mui.min.css" rel="stylesheet" />
</head>

<body ng-app="wyjrApp">
	<div ng-controller="wyjrController">
		<!--<header class="mui-bar mui-bar-nav">
				<h1 class="mui-title">我要接人</h1>
			</header>-->
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
			<h3>操作说明</h3>
			<p>点击我要接人，将该页面分享给要接的微信好友后，好友需点击您分享的链接，随后会定位，并将位置自动发送给您的车机</p>
			<br />
			<button type="button" class="mui-btn mui-btn-blue mui-btn-block"
				ng-click="wyjrClick()">我要接人</button>

		</div>
		<!--end of content-->

	</div>
	<!--end of angular-->

	<script
		src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
	<script src="../js/mui.min.js"></script>
	<script src="../js/urlConfig.js"></script>
	<script type="text/javascript">
		var openId = "${openId}";
	</script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script src="../angular/wyjrApp.js"></script>
	<script src="../angular/wyjrController.js"></script>
</body>

</html>
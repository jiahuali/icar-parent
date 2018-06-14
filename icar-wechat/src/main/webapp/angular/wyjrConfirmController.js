app.controller("wyjrConfirmController", function($scope, $rootScope, $http) {
	mui.init();

	//TODO 线上环境使用
	var ua = window.navigator.userAgent.toLowerCase();
	if(ua.match(/MicroMessenger/i) != 'micromessenger') {
		mui.alert('这是调试环境', '警告', function() {}, {
			type: 'div'
		});
	}

	mui("#sendBtn").button('loading');
	$scope.openId = openId;
	$scope.pickCode = pickCode;
	$scope.operation;
	$scope.pickInfo;

	$scope.sendClick = function() {
		mui.confirm("是否将位置发送给设备？", "确认", ["取消", "确定"], function(e) {

			if(e.index == 1) {

				var data = {
					msgId: $scope.pickCode,
					openId: $scope.openId,
					tuid: $scope.operation.tuid
				};
				//				$http.get("http://icar.s1.natapp.cc/icar-server/v2/function/push2DeviceByTuid", {
				$http.get(urls.push2DeviceByTuid, {
					params: data
				}).then(function(resp) {
					var msg = "";
					if(resp.data.code == 200)
						msg = "发送成功，请在车机上查收";
					else
						msg = resp.data.msg;

					mui.alert(msg, "提示", '确定', function() {}, {
						type: 'div'
					});

				}); //end of get

			} //end of if

		}); //end of confirm
	}; //end of sendClick

	var data = {
		openId: $scope.openId,
		msgId: $scope.pickCode
	}; //end of data

	var location = [116.480983, 40.0958]; //默认值
	var zoom = 15;
	//	$http.get("http://icar.s1.natapp.cc/icar-server/v2/function/getOperationByMsgId", {
	$http.get(urls.getOperationByMsgId, {
		params: data
	}).then(function(resp) {
		if(resp.data.code == 200) {
			mui("#sendBtn").button('reset');
			$scope.operation = resp.data.data;
			$scope.pickInfo = JSON.parse($scope.operation.ext);
			location = [$scope.pickInfo.longitude, $scope.pickInfo.latitude]; //设置地图显示位置

			var map = new AMap.Map('container', {
				resizeEnable: true,
				zoom: zoom,
				center: location
			}); //end of map

			//加载SimpleMarker
			AMapUI.loadUI(['overlay/SimpleMarker'], function(SimpleMarker) {

				//创建SimpleMarker实例
				var marker = new SimpleMarker({
					//前景文字
					iconLabel: {
						innerHTML: "<span><img style='border-radius:32px' width='64px' height='64px' src='" + $scope.pickInfo.headImgUrl + "'/></span>"
					},
					//图标主题
					iconTheme: 'default',
					//背景图标样式
					iconStyle: 'blue',
					//...其他Marker选项...，不包括content
					map: map,

					position: location,
					label: {
						content: $scope.pickInfo.nickName,
						offset: new AMap.Pixel(50, 60)
					}
				}); //end of marker
			}); //end of loadUI

		} else {
			window.location.href = "pages/error.html";
		} //end of else

	}); //end of get

}); //end of controller
app.controller("deviceLocationController", function($scope, $rootScope, $http) {
	var ua = window.navigator.userAgent.toLowerCase();
	mui.init();
	//TODO 线上环境使用
	if(ua.match(/MicroMessenger/i) != 'micromessenger') {
		mui.alert('这是调试环境', '警告', function() {}, {
			type: 'div'
		});
	}

	var data = {
		'openId': openId,
		'token': " "
	};
	//		$http.get("http://icar.s1.natapp.cc/icar-server/v2/device/getDefaultDeviceByOpenId", {
	$http.get(urls.getDefaultDeviceByOpenId, {
		params: data
	}).then(function(resp) {
		console.log(resp);

		if(resp.data.code == 200) {
			$scope.device = resp.data.data;
			var device = $scope.device;
			var location;
			var zoom;
			if(device.longitude == null || device.latitude == null) {
				mui.toast("未读取到设备位置", {
					duration: 'short',
					type: 'div'
				});
				location = [116.480983, 40.0958];
				zoom = 10;
			} else {
				location = [device.longitude, device.latitude];
				zoom = 16;
			}
			var map = new AMap.Map('container', {
				resizeEnable: true,
				zoom: zoom,
				center: location

			});

			map.plugin('AMap.Geolocation', function() {
				geolocation = new AMap.Geolocation({
					enableHighAccuracy: true, //是否使用高精度定位，默认:true
					timeout: 10000, //超过10秒后停止定位，默认：无穷大
					buttonOffset: new AMap.Pixel(10, 20), //定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
					zoomToAccuracy: false, //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
					buttonPosition: 'RB'
				});
				map.addControl(geolocation);
				geolocation.getCurrentPosition();
				AMap.event.addListener(geolocation, 'complete', onComplete); //返回定位信息
				AMap.event.addListener(geolocation, 'error', onError); //返回定位出错信息
			});

			AMap.plugin(['AMap.ToolBar', 'AMap.Scale', 'AMap.OverView'],
				function() {
					map.addControl(new AMap.ToolBar());

					map.addControl(new AMap.Scale());

					map.addControl(new AMap.OverView({
						isOpen: false
					}));
				});

			if(device.longitude == null || device.latitude == null)
				return;
			//加载SimpleMarker
			AMapUI.loadUI(['overlay/SimpleMarker'], function(SimpleMarker) {

				//创建SimpleMarker实例
				var marker = new SimpleMarker({
					//前景文字
					iconLabel: {
						innerHTML: "<span class='mui-icon mui-icon-location'></span>"
					},
					//图标主题
					iconTheme: 'default',
					//背景图标样式
					iconStyle: 'blue',
					//...其他Marker选项...，不包括content
					map: map,

					position: location,
					label: {
						content: device.name,
						offset: new AMap.Pixel(27, 25)
					}
				});

				marker.on('click', function(e) {
					marker.markOnAMAP({
						name: '',
						position: marker.getPosition()
					})
				});

			});

		}
	});

	//解析定位结果
	function onComplete(data) {
		var str = ['定位成功'];
		str.push('经度：' + data.position.getLng());
		str.push('纬度：' + data.position.getLat());
		if(data.accuracy) {
			str.push('精度：' + data.accuracy + ' 米');
		} //如为IP精确定位结果则没有精度信息
		str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
		//document.getElementById('tip').innerHTML = str.join('<br>');
	}
	//解析定位错误信息
	function onError(data) {
		//document.getElementById('tip').innerHTML = '定位失败';
	}

});
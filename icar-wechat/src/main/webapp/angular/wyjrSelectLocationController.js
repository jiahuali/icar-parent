app.controller("wyjrSelectLocationController", function($scope, $http) {
	mui.init();
	//初始化相关值
	$scope.openId = openId //openId
	$scope.pickCode = pickCode //接人码
	$scope.nickName = nickName; //要接的人的昵称
	$scope.headImgUrl = headImgUrl; //要接的人的头像
	$scope.location;
	$scope.poi;

	var data = {
		msgId: $scope.pickCode
	};

	//	$http.get("http://icar.s1.natapp.cc/icar-server/v2/function/getOperationStatusByMsgId", {
	$http.get(urls.getOperationStatusByMsgId, {
		params: data
	}).then(function(resp) {
		if(resp.data.code != 200 || resp.data.data != 1) { //该pickCode未过期且可用
			mui.alert('该链接已失效', '错误', function() {
				//				window.location.href = "http://icar.s1.natapp.cc/icar-wechatmp/pages/error.html";
				window.location.href = urls.pagesUrl + "error.html";
			}, {
				type: 'div'
			}); //end oif alert
		} //end of if
	}); //end of get

	$scope.pickMeClick = function() {
		var openId = $scope.openId;
		var data = {
			longitude: $scope.location.longitude,
			latitude: $scope.location.latitude,
			openId: $scope.openId,
			pickCode: $scope.pickCode,
			nickName: $scope.nickName,
			headImgUrl: $scope.headImgUrl
		}; //end of data

		//		$http.get("http://icar.s1.natapp.cc/icar-wechatmp/function/wyjrLocationBack.do", {
		$http.get(urls.wyjrLocationBack, {
			params: data
		}).then(function(resp) {
			var msg = "";
			if(resp.data.code == 200) {
				msg = "已经向小伙伴发出求救^~^";
			} else {
				msg = resp.data.msg;
			}
			mui.alert(msg, '提示', '确定', function() {}, {
				type: 'div'
			});
		}); //end of get 

	};
	//	mui("#sendBtn").button('loading');
	var map = new AMap.Map('container', {
		resizeEnable: true,
		zoom: 14,
		center: [116.480983, 40.0958]

	}); //end of new map

	map.plugin('AMap.Geolocation', function() {
		geolocation = new AMap.Geolocation({
			enableHighAccuracy: true, //是否使用高精度定位，默认:true
			timeout: 10000, //超过10秒后停止定位，默认：无穷大
			buttonOffset: new AMap.Pixel(10, 20), //定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
			zoomToAccuracy: false, //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
			buttonPosition: 'RB'
		}); //end of location

		map.addControl(geolocation);
		geolocation.getCurrentPosition();
		AMap.event.addListener(geolocation, 'complete', onComplete); //返回定位信息
		AMap.event.addListener(geolocation, 'error', onError); //返回定位出错信息
	}); //end of plugin

	AMap.plugin(['AMap.ToolBar', 'AMap.Scale', 'AMap.OverView'],
		function() {
			map.addControl(new AMap.ToolBar());

			map.addControl(new AMap.Scale());

			map.addControl(new AMap.OverView({
				isOpen: false
			}));
		}); //end of plugin

	AMapUI.loadUI(['misc/PoiPicker'], function(PoiPicker) {

		var poiPicker = new PoiPicker({
			city: 'auto',
			input: 'pickerInput'
		});

		//初始化poiPicker
		poiPickerReady(poiPicker);
	});

	function poiPickerReady(poiPicker) {

		window.poiPicker = poiPicker;

		var marker = new AMap.Marker();

		var infoWindow = new AMap.InfoWindow({
			offset: new AMap.Pixel(0, -20)
		});

		//选取了某个POI
		poiPicker.on('poiPicked', function(poiResult) {

			var source = poiResult.source,
				poi = poiResult.item,
				info = {
					source: source,
					id: poi.id,
					name: poi.name,
					location: poi.location.toString(),
					address: poi.address
				}; //end of source
			$scope.poi = poi;

			marker.setMap(map);
			infoWindow.setMap(map);

			marker.setPosition(poi.location);
			infoWindow.setPosition(poi.location);
			infoWindow.open(map, marker.getPosition());
			mui.confirm("是否设置" + poi.name + "为目的地", "确认", ["取消", "确定"], function(e) {
				if(e.index == 1) {
					console.log(JSON.stringify($scope.poi));
					$scope.location = {
						longitude: $scope.poi.location.lng,
						latitude: $scope.poi.location.lat
					};
				}
			}); //end of confirm

			infoWindow.setContent('POI信息: <pre>' + JSON.stringify(info, null, 1) + '</pre>');

		}); //end of on

		poiPicker.onCityReady(function() {
			//poiPicker.suggest('美食');
		}); //end of poiready
	}; //end of picker

	//解析定位结果
	function onComplete(data) {
		mui("#sendBtn").button('reset');
		$scope.location = {
			longitude: data.position.getLng(),
			latitude: data.position.getLat()
		};

		var str = ['定位成功'];
		str.push('经度：' + data.position.getLng());
		str.push('纬度：' + data.position.getLat());
		if(data.accuracy) {
			str.push('精度：' + data.accuracy + ' 米');
		} //如为IP精确定位结果则没有精度信息
		str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
	} //end of complete

	//解析定位错误信息
	function onError(data) {
		//document.getElementById('tip').innerHTML = '定位失败';
		//alert('定位失败' + JSON.stringify(data));
	} //end of error

}); //end of controller
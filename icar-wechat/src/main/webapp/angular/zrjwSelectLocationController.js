app.controller("zrjwSelectLocationController", function($scope, $http) {
	mui.init();
	mui("#sendBtn").button('loading');
	//初始化相关值
	$scope.openId = openId //openId
	$scope.nickName = nickName; //要接的人的昵称
	$scope.headImgUrl = headImgUrl; //要接的人的头像
	$scope.location;
	$scope.poi;

	$scope.pickMeClick = function() {

		var data = {
			'openId': openId,
			'operation': 3
		};
		$http.get(urls.generatePickCodeByOpenId, {
			params: data
		}).then(function suc(resp) {
				console.log(resp);
				if(resp.data.code == 200) { //request success
					$scope.pickCode = resp.data.data;
					//TODO :alert
					mui.alert("点击下方确认后，请点击您当前界面的右上角“发送给朋友”，选择您的好友并发送来完成操作", "提示", "确认", function() {
						if($scope.pickCode.length == 0) {
							mui.alert("网络似乎在开小差，请关闭后重试", "错误", "确定", function() {});
							return;
						}
						var openId = $scope.openId;
						var ext = {
							longitude: $scope.location.longitude,
							latitude: $scope.location.latitude,
							openId: $scope.openId,
							pickCode: $scope.pickCode,
							nickName: $scope.nickName,
							headImgUrl: $scope.headImgUrl
						}; //end of data
						var data = {
							'msgId': $scope.pickCode,
							'ext': JSON.stringify(ext)
						};
						//here update
						$http({
							method: "POST",
							//							url: "http://icar.s1.natapp.cc/icar-server/v2/function/updateMsgByMsgId",
							url: urls.updateMsgByMsgId,
							data: data
						}).success(function(resp) {
							//mui("#submitBtn").button('reset');
							if(resp.code != 200) {
								mui.alert(resp.msg, "错误", "确定", function() {});
								return;
							}
							var link = urls.gotoZrjwConfirm + "?pickCode=" + $scope.pickCode;
							wx.onMenuShareAppMessage({
								title: '快来接我！',
								desc: '点击这个页面，查看小伙伴的位置，火速前往支援！',
								link: link,
								imgUrl: urls.imgUrl + "logo.png",
								success: function() {}, //end of success
								trigger: function() {} //end of trigger
							}); // end of shareAppMessage 

						}).error(function(resp) {
							mui.alert("请检查您的网络后重试！", "错误", "确定", function() {});
						}); //end of post

					}); //end of alert
				} else { //generatePikcode fail
					mui.alert(resp.data.msg, "错误", "确定", function() {});
					return;
				} //end of else
			},
			function fail(resp) {
				mui.alert("网络似乎在开小差，请关闭后重试", "错误", "确定", function() {});
				return;
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
	function onError(data) {} //end of error

	var url = location.href.split('#').toString();
	var data = {
		'url': url
	};

	$http.get(urls.getConfig, {
		params: data
	}).then(function(resp) {
		//请求成功，注入
		console.log(resp);
		var configData = resp.data;
		wx.config({
			'debug': false,
			'appId': configData.appId,
			'timestamp': configData.timestamp,
			'nonceStr': configData.nonceStr,
			'signature': configData.signature,
			'jsApiList': [
				'checkJsApi', //判断当前客户端版本是否支持指定js接口
				'onMenuShareTimeline', //分享到朋友圈
				'onMenuShareAppMessage' //分享给朋友
			]
		}); //end of config
	}); //end of get
	wx.ready(function() {
		wx.checkJsApi({
			jsApiList: [
				'onMenuShareAppMessage' //分享给朋友
			],
			success: function(resp) {}
		}); //end of checkjs

	}); //end of ready

}); //end of controller
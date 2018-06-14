app.controller("wyjrController", function($scope, $http) {

	mui.init();
	//TODO 线上环境使用
	var ua = window.navigator.userAgent.toLowerCase();
	if(ua.match(/MicroMessenger/i) != 'micromessenger') {
		mui.alert('这是调试环境', '警告', function() {}, {
			type: 'div'
		});
	}
	var data = {
		'openId': openId,
		'token': " "
	};

	$http.get(urls.getDefaultDeviceByOpenId, {
		params: data
	}).then(function(resp) {
		if(resp.data.code != 200) {
			mui.alert("您还没有默认设备，请扫描添加后使用该功能!", "提示", '确定', function() {
				wx.closeWindow();
			}, {
				type: 'div'
			});
		} //end of if
	}); //end of get

	//获得slider插件对象
	var gallery = mui('.mui-slider'); 
	gallery.slider({
		interval: 3000 //自动轮播周期，若为0则不自动播放，默认为0；
	}); //end of gallery

	$scope.pickCode = ""; //接人码

	$scope.wyjrClick = function() {

		mui.alert("点击下方确认后，请点击您当前界面的右上角“发送给朋友”，选择您的好友并发送来完成操作", "提示", "确认", function() {

			if($scope.pickCode.length != 0)
				return;

			var data = {
				'openId': openId,
				'operation':2
			};

			//			$http.get('http://icar.s1.natapp.cc/icar-server/v2/function/generatePickCodeByOpenId', {
			$http.get(urls.generatePickCodeByOpenId, {
				params: data
			}).then(function(resp) {
				console.log(resp);
				if(resp.data.code === 200) {
					var msg = "";
					$scope.pickCode = resp.data.data;
					//					var link = "http://icar.s1.natapp.cc/icar-wechatmp/function/gotoWyjrSelectLocation.do?pickCode=" + $scope.pickCode;
					var link = urls.gotoWyjrSelectLocation + "?pickCode=" + $scope.pickCode;
					wx.onMenuShareAppMessage({
						title: '我来接你啦！',
						desc: '点击这个页面，把位置发给我，我就会来接你哦！',
						link: link,
						imgUrl: urls.imgUrl + "logo.png",
						//						imgUrl:"http://icar.s1.natapp.cc/icar-wechatmp/img/logo.png",
						success: function() {

						}, //end of success
						trigger: function() {
							//							var tip = "";
							//							if($scope.pickCode === "")
							//								tip = "您还没有点击“我要接人”生成二维码，否则无法成功使用我要接人功能哦";
							//							else
							//								tip = "发送给好友成功!";
							//							mui.alert(tip, "提示", '确定', function() {}, {
							//								type: 'div'
							//							});
						} //end of trigger
					}); // end of shareAppMessage
					msg = "获取接人码成功，现在快去分享给好友吧！";
				} //end of if
				else {
					msg = resp.data.msg;
				} //end of else
				mui.alert(msg, "提示", '确定', function() {}, {
					type: 'div'
				});
			}); //end of get
		}); //end of alert
	} //end of wyjrClick

	var url = location.href.split('#').toString();
	var data = {
		'url': url
	};

	//	$http.get("http://icar.s1.natapp.cc/icar-wechatmp/function/getConfig.do", {
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
			success: function(resp) {
				//				alert("校验成功:" + JSON.stringify(res));
			}
		}); //end of checkjs

	}); //end of ready

}); //end of controller
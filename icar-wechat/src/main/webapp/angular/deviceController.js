app.controller("deviceController", function($scope, $rootScope, $http) {
	mui.init();
	//TODO 线上环境使用
	var ua = window.navigator.userAgent.toLowerCase();
	if(ua.match(/MicroMessenger/i) != 'micromessenger') {
		mui.alert('这是调试环境', '警告', function() {}, {
			type: 'div'
		});
	}

	var data = {
		'openId': openId
	};

	$http.get(urls.listDeviceByOpenId, {
		params: data
	}).then(function(resp) {
		console.log(resp);
		$rootScope.deviceList = resp.data.data;
		mui.toast("数据加载成功", {
			duration: 'short',
			type: 'div'
		});
	});

	var data = {
		'openId': openId,
		'token': " "
	};
	$scope.defaultDevice = "";
	$http.get(urls.getDefaultDeviceByOpenId, {
		params: data
	}).then(function(resp) {
		if(resp.data.code == 200) {
			$scope.defaultDevice = resp.data.data.deviceCustom.tuid;
		} //end of if
	}); //end of get

	$scope.isDefault = function(index) {
		if($scope.defaultDevice === $scope.deviceList[index].tuid) {
			return true;
		}
		return false;
	};

	$scope.addDevice = function() {
		//TODO 实现添加设备
		mui.prompt("如果要通过扫描二维码形式添加，请关闭当前页面，并打开微信扫一扫功能。您也可以手动输入车机上的序列号", "设备序列号", "添加设备", ["确定", "取消"], function(e) {

			if(e.index == 1) { //添加
				if(e.value.length != 32) {
					mui.toast("请输入正确的序列号!!", {
						duration: "short",
						type: "div",
					});
					return;
				}
				var data = {
					'openId': openId,
					'tuid': e.value,
					'token': " "
				};

			}

		});
	}

	$scope.changeName = function(index) {
		var device = $scope.deviceList[index];
		mui.prompt("修改设备名", "设备名", "确认", ["取消", "确定"], function(e) {
			if(e.value === "" || e.value === device.name)
				return;
			if(e.index == 1) { //确定修改
				var data = {
					'openId': openId,
					'tuid': device.tuid,
					'name': e.value,
					'token': " "
				};

				$http({
					method: "POST",
					//					url: "http://icar.s1.natapp.cc/icar-server/v2/device/updateDeviceNameByTuidAndOpenId",
					url: urls.updateDeviceNameByTuidAndOpenId,
					data: data
				}).success(function(resp) {
					var msg = "";
					if(resp.code == 200) {
						$scope.deviceList[index].name = e.value;
						//						device.name = e.value;
						$scope.$applyAsync();
						msg = "修改成功";
					} else {
						msg = resp.msg;
					}
					//修改后的toast
					mui.toast(msg, {
						duration: "short",
						type: "div"
					});
				});

			}
		});
	};

	$scope.setAsDefault = function(index) {
		var device = $scope.deviceList[index];
		mui.confirm("是否将设备" + device.name + "设为默认设备？", "确认", ["取消", "确定"], function(e) {
			if(e.index == 1) {
				var data = {
					'openId': openId,
					'isDefault': 1,
					'token': Math.random() + "",
					'tuid': device.tuid
				};
				$http({
					method: "POST",
					//					url: "http://icar.s1.natapp.cc/icar-server/v2/device/updateDeviceAsDefaultByTuidAndOpenId",
					url: urls.updateDeviceAsDefaultByTuidAndOpenId,
					data: data
				}).success(function(resp) {
					console.log(resp);
					var msg = "";
					if(resp.code == 200) {
						msg = "已将设备" + device.name + "设为默认设备";
						$scope.defaultDevice = device.tuid;
						$scope.deviceList[index].isDefault = $rootScope.editName;
						$scope.$applyAsync();
					} else if(resp.code == 1009) {
						msg = "您的设备序列号无效!";
					} else {
						msg = resp.msg;
					}
					mui.toast(msg, {
						duration: "short",
						type: "div",
					});
				});
			}
		});
	};

	$scope.deleteDevice = function(index) {
		var device = $scope.deviceList[index];
		mui.confirm("该操作会清除您当前云端的数据，您真的要取消绑定设备？", "警告", ["取消", "确定"], function(e) {
			if(e.index == 1) {
				//TODO 更新
				mui.toast("正在删除设备", {
					duration: "short",
					type: "div"
				});
				var data = {
					'openId': openId,
					'tuid': device.tuid,
					'token': " "
				};
				$http({
					method: "POST",
					//					url: "http://icar.s1.natapp.cc/icar-server/v2/device/deleteDeviceByTuidAndOpenId",
					url: urls.deleteDeviceByTuidAndOpenId,
					data: data
				}).success(function(resp) {
					var msg = "";
					if(resp.code == 200) {
						msg = "删除设备成功";
						$scope.deviceList.splice(index, 1);
						$scope.$applyAsync();
					} else {
						msg = resp.msg;
					}
					mui.toast(msg, {
						duration: "short",
						type: "div",
					});
				});
			}
		});
	};

});
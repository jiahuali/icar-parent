app.controller("newIdeaCtr", function($scope, $http) {

	mui.init();
	//	mui.plusReady(function() {
	//		console.log("redy");
	//		alert(plus.webview.currentWebview().openId);
	//	});
	var openId = localStorage.getItem("openId");
	$scope.title = "";
	$scope.content = "";

	$scope.submitBtn = function() {
		//前端校验
		if($scope.title.length == 0) {
			mui.alert("标题不能为空,请填写标题后提交", "错误", "确定", function() {});
			return;
		}

		if($scope.content.length == 0) {
			mui.alert("正文不能为空,请填写正文后提交", "错误", "确定", function() {});
			return;
		}

		//		mui("#submitBtn").button('reset');
		mui("#submitBtn").button('loading');

		var data = {
			'openId': openId,
			'title': $scope.title,
			'content': $scope.content
		};

		$http({
			method: "POST",
			//					url: "http://icar.s1.natapp.cc/icar-server/v2/device/updateDeviceNameByTuidAndOpenId",
			url: urls.addIdeaByOpenId,
			data: data
		}).success(function(resp) {
			mui("#submitBtn").button('reset');
			var msg = "";
			if(resp.code == 200)
				msg = "您已提交创意，谢谢您的好点子!";
			else
				msg = resp.msg;

			mui.alert(msg, "消息", "确定", function() {
				if(resp.code == 200)
					mui.back();
			});
		}).fail(function() {
			mui.alert("请检查您的网络后重试！", "错误", "确定", function() {});
		}); //end of post

	};

	$scope.backBtn = function() {
		mui.back();
	};
});
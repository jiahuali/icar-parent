app.controller("ideaDetailCtr", function($scope, $http) {

	mui.init();
	var openId = localStorage.getItem("openId");
	$scope.ideaDetail = JSON.parse(localStorage.getItem("ideaDetail"));
	$scope.newFeedbackText = "";

	$scope.send = function() {
		if($scope.newFeedbackText === "") {
			mui.alert(resp.data.msg, "提示", '确定', function() {});
			return;
		}
		if($scope.openId === "") {
			//TODO 添加掉线操作
		}
		var data = {
			'ideaUuid': $scope.ideaDetail.uuid,
			'content': $scope.newFeedbackText,
			'token': ' '
		};
		mui("#submitBtn").button('loading');
		$http({
			method: "POST",
			//					url: "http://icar.s1.natapp.cc/icar-server/v2/device/updateDeviceNameByTuidAndOpenId",
			url: urls.addFeedbackByIdeaUuid,
			data: data
		}).success(function(resp) {
			mui("#submitBtn").button('reset');
			var msg = "";
			if(resp.code == 200) {
				$scope.listResult.code = 200;
				if($scope.listResult.data == null) {
					$scope.listResult.data = [];
				}
				$scope.listResult.data.push(resp.data);
				msg = "回复成功";
			} else {
				msg = resp.msg;
			}

			mui.alert(msg, "消息", "确定", function() {});
		}).fail(function() {
			mui.alert("请检查您的网络后重试！", "错误", "确定", function() {});
		}); //end of post

	};

	var data = {
		'token': ' ',
		'ideaUuid': $scope.ideaDetail.uuid
	};

	$http.get(urls.listFeedbacksByIdeaUuid, {
		params: data
	}).then(function(resp) {
		$scope.listResult = resp.data;
		if(resp.data.code === 200) {
			//数据加载成功
			//$scope.feedbackList = resp.data.data;
		} else if(resp.data.code === 1002) {
			//当前还没评论
			//TODO m没有评论
		}
		console.log(resp);
	}); //end of get

});
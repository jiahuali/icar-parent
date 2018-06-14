app.controller("newPainCtr", function($scope, $http) {

	mui.init();
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

		mui("#submitBtn").button('loading');

//		mui.alert("功能正在开发中，敬请期待!", "提示", "确定", function() {
//			mui("#submitBtn").button('reset');
//		});
				var data = {
					'openId': openId,
					'title': $scope.title,
					'content': $scope.content
				};
		
				$http({
					method: "POST",
					url: urls.addIdeaByOpenId,
					data: data
				}).success(function(resp) {
					mui("#submitBtn").button('reset');
					var msg = "";
					if(resp.code == 200)
						msg = "您已提交痛点，谢谢您的反馈!";
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
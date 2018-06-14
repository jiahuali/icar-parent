app.controller("myIdeaCtr", function($scope, $http) {

	mui.init();

	//TODO 测试用，正式环境使用下面
//	var openId = "ojI2Kwjtfdi7RuRinELS2TeAQgwo";
		var openId = localStorage.getItem("openId");

	var data = {
		'openId': openId
	};
	$http.get(urls.listIdeasByOpenId, {
		params: data
	}).then(function(resp) {

		if(resp.data.code != 200) {
			mui.alert(resp.data.msg, "提示", '确定', function() {
				mui.back();
			});

		}
		$scope.ideaList = resp.data.data;
		console.log(resp);
	});

	$scope.detailClick = function($index) {
		localStorage.setItem("ideaDetail", JSON.stringify($scope.ideaList[$index]));
		mui.openWindowWithTitle({
			url: '../pages/idea_detail.html',
			id: 'detail',
		}, {
			title: { //标题配置
				text: $scope.ideaList[$index].title, //标题文字
			},
			back: { //左上角返回箭头
				image: {
					base64Data: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAMAAABg3Am1AAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAb1BMVEUAAAAAev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8Aev8AAACubimgAAAAI3RSTlMAGfUTGfQTGPMSGPIYGhgaGBsXGxcbFxwXHBccFhwWHRYdHWufDPQAAAABYktHRACIBR1IAAAAB3RJTUUH4QETEBwooeTlkQAAAJVJREFUSMft1EkSgkAQRNFGUXFWHBDBibr/HTUwD5B/48Ig1y+io7u6MqUhf5hsNEY+j5hMgZ/FJ8Xc9ovos3T96utjbfqN/Nb0O/m96Uv5g+mP8ifTn+Ur01/ka9Nf5RvTt/I309/lH6Z/yr9Mn+Q71/MT8B34K/E58Enzv8R/K98HvnF8p3lr8F7izce7lbf3kJ/lDQp9HdBhgg3PAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDE3LTAxLTE5VDE2OjI4OjQwKzA4OjAwpTDFwQAAACV0RVh0ZGF0ZTptb2RpZnkAMjAxNy0wMS0xOVQxNjoyODo0MCswODowMNRtfX0AAAAASUVORK5CYII='
				}
			}
		})

	};

});
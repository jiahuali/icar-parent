app.controller("socketController", function($scope, $http) {

	mui.init();

	$scope.sendText = "";
	$scope.respText = "";

	$scope.send = function() {
		//		ws.send($scope.sendText);
		ws.send("fuck");
	};

	// 打开一个 web socket
	var ws = new WebSocket("ws://icar.s1.natapp.cc/icar-server/websocket");

	ws.onopen = function() {
		// Web Socket 已连接上，使用 send() 方法发送数据
		//		ws.send("发送数据");
		console.log('发送数据');
	};

	ws.onmessage = function(evt) {
		var received_msg = evt.data;
		console.log(evt);
	};

	ws.onclose = function() {
		// 关闭 websocket
		alert("连接已关闭...");
	};

	//	var socket = new io.Socket('localhost', {
	//		port: 80
	//	});
	//	socket.connect();

});
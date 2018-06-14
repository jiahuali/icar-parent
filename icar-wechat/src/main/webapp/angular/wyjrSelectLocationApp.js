var app = angular.module("wyjrSelectLocationApp", []);
app.config(function($httpProvider) {
	$httpProvider.defaults.transformRequest = function(obj) {
		var str = [];
		for(var p in obj) {
			str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		}
		return str.join("&");
	};
	$httpProvider.defaults.headers.post = {
		'Content-Type': 'application/x-www-form-urlencoded'
	}

});
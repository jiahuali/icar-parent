var protocol = "http";

var domain = "icar.natapp1.cc";

var version_v1 = "v1";
var version_v2 = "v2";
var icar_server = "icar-server";
var icar_wechatmp = "icar-wechat";
var urls = {
	listDeviceByOpenId : protocol + "://" + domain + "/" + icar_wechatmp + "/"
			+ "device/listDeviceByOpenId",
	updateDeviceNameByTuidAndOpenId : protocol + "://" + domain + "/"
			+ icar_wechatmp + "/"
			+ "device/updateDeviceNameByTuidAndOpenId",
	updateDeviceAsDefaultByTuidAndOpenId : protocol + "://" + domain + "/"
			+ icar_wechatmp + "/"
			+ "device/updateDeviceAsDefaultByTuidAndOpenId",
	deleteDeviceByTuidAndOpenId : protocol + "://" + domain + "/" + icar_wechatmp
			+ "/"+ "device/deleteDeviceByTuidAndOpenId",
	getDefaultDeviceByOpenId : protocol + "://" + domain + "/" + icar_wechatmp
			+ "/" + "device/getDefaultDeviceByOpenId",
	push2DeviceByTuid : protocol + "://" + domain + "/" + icar_wechatmp
			+ "/" + "function/push2DeviceByTuid",
	getOperationByMsgId : protocol + "://" + domain + "/" + icar_wechatmp + "/"
			+ "function/getOperationByMsgId",
	generatePickCodeByOpenId : protocol + "://" + domain + "/" + icar_wechatmp
			+ "/" + "function/generatePickCodeByOpenId",
	gotoWyjrSelectLocation : protocol + "://" + domain + "/" + icar_wechatmp
			+ "/" + "pages/gotoWyjrSelectLocation.do",
	gotoZrjwConfirm : protocol + "://" + domain + "/" + icar_wechatmp + "/"
			+ "pages/gotoZrjwConfirm.do",
	imgUrl : protocol + "://" + domain + "/" + icar_wechatmp + "/" + "img/",
	getConfig : protocol + "://" + domain + "/" + icar_wechatmp + "/"
			+ "pages/getConfig.do",
	getOperationStatusByMsgId : protocol + "://" + domain + "/" + icar_wechatmp
			+ "/" + "function/getOperationStatusByMsgId",
	pagesUrl : protocol + "://" + domain + "/" + icar_wechatmp + "/" + "pages/",
	wyjrLocationBack : protocol + "://" + domain + "/" + icar_wechatmp + "/"
			+ "pages/wyjrLocationBack.do",
	addIdeaByOpenId : protocol + "://" + domain + "/" + icar_wechatmp + "/"
			  + "idea/addIdeaByOpenId",
	listIdeasByOpenId : protocol + "://" + domain + "/" + icar_wechatmp + "/"
			  + "idea/listIdeaByOpenId",
	listFeedbacksByIdeaUuid : protocol + "://" + domain + "/" + icar_wechatmp
			  + "/" + "feedback/listFeedbacksByIdeaUuid",
	addFeedbackByIdeaUuid : protocol + "://" + domain + "/" + icar_wechatmp + "/"
			  + "feedback/addFeedbackByIdeaUuid",
	zrjwBack : protocol + "://" + domain + "/" + icar_wechatmp + "/"
			  + "pages/zrjwBack.do",
	updateMsgByMsgId : protocol + "://" + domain + "/" + icar_wechatmp + "/"
			  + "function/updateMsgByMsgId"
};
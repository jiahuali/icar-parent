var protocol = "http";

var domain = "icar.s1.natapp.cc";

var version_v1 = "v1";
var version_v2 = "v2";
var icar_server = "icar-server";
var icar_wechatmp = "icar-wechatmp";
var urls = {
	listDeviceByOpenId: protocol + "://" + domain + "/" + icar_server + "/" + version_v1 + "/" + "device/listDeviceByOpenId",
	updateDeviceNameByTuidAndOpenId: protocol + "://" + domain + "/" + icar_server + "/" + version_v2 + "/" + "device/updateDeviceNameByTuidAndOpenId",
	updateDeviceAsDefaultByTuidAndOpenId: protocol + "://" + domain + "/" + icar_server + "/" + version_v2 + "/" + "device/updateDeviceAsDefaultByTuidAndOpenId",
	deleteDeviceByTuidAndOpenId: protocol + "://" + domain + "/" + icar_server + "/" + version_v2 + "/" + "device/deleteDeviceByTuidAndOpenId",
	getDefaultDeviceByOpenId: protocol + "://" + domain + "/" + icar_server + "/" + version_v2 + "/" + "device/getDefaultDeviceByOpenId",
	push2DeviceByTuid: protocol + "://" + domain + "/" + icar_server + "/" + version_v2 + "/" + "function/push2DeviceByTuid",
	getOperationByMsgId: protocol + "://" + domain + "/" + icar_server + "/" + version_v2 + "/" + "function/getOperationByMsgId",
	generatePickCodeByOpenId: protocol + "://" + domain + "/" + icar_server + "/" + version_v2 + "/" + "function/generatePickCodeByOpenId",
	gotoWyjrSelectLocation: protocol + "://" + domain + "/" + icar_wechatmp + "/" + "function/gotoWyjrSelectLocation.do",
	gotoZrjwConfirm: protocol + "://" + domain + "/" + icar_wechatmp + "/" + "function/gotoZrjwConfirm.do",
	imgUrl: protocol + "://" + domain + "/" + icar_wechatmp + "/" + "img/",
	getConfig: protocol + "://" + domain + "/" + icar_wechatmp + "/" + "function/getConfig.do",
	getOperationStatusByMsgId: protocol + "://" + domain + "/" + icar_server + "/" + version_v2 + "/" + "function/getOperationStatusByMsgId",
	pagesUrl: protocol + "://" + domain + "/" + icar_wechatmp + "/" + "pages/",
	wyjrLocationBack: protocol + "://" + domain + "/" + icar_wechatmp + "/" + "function/wyjrLocationBack.do",
	addIdeaByOpenId: protocol + "://" + domain + "/" + icar_server + "/" + version_v2 + "/" + "idea/addIdeaByOpenId",
	listIdeasByOpenId: protocol + "://" + domain + "/" + icar_server + "/" + version_v2 + "/" + "idea/getIdeaListByOpenId",
	listFeedbacksByIdeaUuid: protocol + "://" + domain + "/" + icar_server + "/" + version_v2 + "/" + "feedback/getFeedbackListByIdeaUuid",
	addFeedbackByIdeaUuid: protocol + "://" + domain + "/" + icar_server + "/" + version_v2 + "/" + "feedback/addFeedbackByIdeaUuid",
	zrjwBack: protocol + "://" + domain + "/" + icar_wechatmp + "/" + "function/zrjwBack.do",
	updateMsgByMsgId: protocol + "://" + domain + "/" + icar_server + "/" + version_v2 + "/" + "function/updateMsgByMsgId"
};
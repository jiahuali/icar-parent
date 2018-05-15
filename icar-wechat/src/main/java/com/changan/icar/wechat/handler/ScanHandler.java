package com.changan.icar.wechat.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Device;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.wechat.service.DeviceService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class ScanHandler extends AbstractHandler {

	@Autowired
	private DeviceService deviceService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager wxSessionManager) throws WxErrorException {
		this.logger.info("**SCANHandler***我扫了二维码" + wxMpXmlMessage.getFromUser());

		// 添加车辆功能
		// 已关注后扫码"eventKey" : "bind:100222,S22"
		// 扫码关注并添加车机"eventKey" : "qrscene_bind:100222,S22"
		String eventKey = wxMpXmlMessage.getEventKey();
		String resultMsg = "";
		if (eventKey.indexOf("qrscene_bind") == 0 || eventKey.indexOf("bind") == 0) {
			String[] paramArr = eventKey.split(":")[1].split(",");
			String tuid = paramArr[0];
			String model = paramArr[1];
			// String type = null;
			// if (paramArr.length == 3)
			// type = paramArr[2];

			if (StringUtils.isNull(tuid, model)) {
				resultMsg = "扫描的二维码非法！";
			} else {
				// 发起请求，根据openId添加设备
				Device device = new Device();
				device.setTuid(tuid);
				device.setModel(model);
				device.setUuid(wxMpXmlMessage.getFromUser());
				Result result = deviceService.addDevice(tuid, wxMpXmlMessage.getFromUser(), model, device);
				if (result.getCode() == 200) {
					resultMsg = "添加设备成功！";
				} else {
					LogUtils.error("添加设备失败,msg:" + result.getMsg());
					resultMsg = "添加失败";
				}
			}
			return WxMpXmlOutMessage.TEXT()

					.content(resultMsg)

					.fromUser(wxMpXmlMessage.getToUser())

					.toUser(wxMpXmlMessage.getFromUser())

					.build();

		} else {// 不是添加车机
			return null;
		}
	}

}

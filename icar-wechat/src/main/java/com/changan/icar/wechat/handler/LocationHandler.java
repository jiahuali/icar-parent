package com.changan.icar.wechat.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.changan.icar.comm.dto.IcarConst;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Operation;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.wechat.service.OperationService;
import com.google.gson.Gson;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 用于处理快速导航事件
 * 
 * @author ljh
 *
 */
@Component
public class LocationHandler extends AbstractHandler {
	@Autowired
	private OperationService operationService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) {
		String resultMsg = "";
		if (wxMessage.getMsgType().equals(WxConsts.XML_MSG_LOCATION)) {
			// TODO 接收处理用户发送的地理位置消息
			try {
				// TODO 验证这是否是正确的坐标系
				Double x = wxMessage.getLocationX();
				Double y = wxMessage.getLocationY();
				// 坐标系类型，高德和腾讯是GCJ02，百度为BD09
				String coordType = "GCJ02";
				// 地点名
				String name = wxMessage.getLabel();
				// 非响应请求
				Integer type = IcarConst.OPERATION_TYPE_NONERESP_REQUEST;
				// 导航控制
				Integer operationType = IcarConst.OPERATION_OPER_NAVI;

				String openId = wxMessage.getFromUser();

				Map<String, Object> extMap = new HashMap<>();
				extMap.put("longitude", y);
				extMap.put("latitude", x);
				extMap.put("coordType", coordType);
				extMap.put("name", name);
				String ext = new Gson().toJson(extMap);
				Operation operation = new Operation();
				operation.setExt(ext);
				operation.setType(type);
				operation.setOperation(operationType);
				operation.setUuid(openId);
				// 执行指令
				Result result = operationService.execute(openId, operation);
				if (result.getCode() != 200) {
					LogUtils.error("执行指令失败,msg:" + result.getMsg());
					resultMsg = "位置发送失败，请稍后重试";
				} else {
					resultMsg = "位置已发送到您的爱车上啦";
				}
			} catch (Exception e) {
				this.logger.error("位置消息接收处理失败", e);
				resultMsg = "位置发送失败，请稍后重试";
			}
		}

		return WxMpXmlOutMessage.TEXT()

				.fromUser(wxMessage.getToUser())

				.toUser(wxMessage.getFromUser())

				.content(resultMsg)

				.build();
	}

}

package com.changan.icar.wechat.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.changan.icar.comm.dto.IcarConst;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;
import com.changan.icar.wechat.builder.TextBuilder;
import com.changan.icar.wechat.service.AccountService;
import com.changan.icar.wechat.service.impl.WeixinService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Component
public class SubscribeHandler extends AbstractHandler {

	private ScanHandler scanHandler = new ScanHandler();
	@Autowired
	private AccountService accountService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {

		this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

		// 1、判断用户是否有账号，若没有，创建新账号
		Map<String, String> regParams = new HashMap<>();
		regParams.put("openId", wxMessage.getFromUser());
		regParams.put("token", "");
		Result accResult = accountService.getAccountByOpenId(wxMessage.getFromUser());
		this.logger.info("accResult:" + accResult.toString());
		WeixinService weixinService = (WeixinService) wxMpService;
		if (accResult.getCode() == 200 && accResult.getData() != null) {
			// 账号已存在，之前关注后取消的
			this.logger.info("账号已存在，欢饮回来" + wxMessage.getFromUser());
		} else {
			// 获取微信用户基本信息
			WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);
			Account account = new Account();
			String openId = userWxInfo.getOpenId();
			String username = userWxInfo.getNickname();
			account.setPicUrl(userWxInfo.getHeadImgUrl());
			account.setUsername(username);
			account.setUuid(openId);
			account.setUserFrom(IcarConst.ACCOUNT_FROM_WX);
			accountService.addAccountByOpenId(openId, username, account);
		}

		// 2、判断用户是通过何种渠道关注公众号。若是通过搜索添加，返回谢谢关注,若是二维码，走步骤3
		// 3、判断用户扫描二维码中是否含有eventKey携带的车机数据，若无，返回谢谢关注，若有，走步骤4
		// 4、将处理步骤转给ScanHandler
		WxMpXmlOutMessage responseResult = null;
		// 处理特殊
		try {
			responseResult = handleSpecial(wxMessage, context, wxMpService, sessionManager);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		// 通过扫描携带车机二维码进入
		if (responseResult != null) {
			return responseResult;
		}
		// 非车机二维码，或者是普通搜索关注
		try {
			return new TextBuilder().build("感谢关注", wxMessage, weixinService);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}

		return null;
	}

	/**
	 * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
	 */
	protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) throws Exception {
		// TODO
		// 扫码
		return null;

	}

	protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage, Map<String, Object> context,
			WxMpService wxMpService, WxSessionManager sessionManager) throws Exception {
		if (wxMessage.getEventKey().indexOf("qrscene_bind") == 0) {
			// 扫描携带tuid，model的二维码,将处理权交给ScanHandler
			this.logger.info(wxMessage.getFromUser() + "扫描了携带tuid和model的二维码");
			return scanHandler.handle(wxMessage, context, wxMpService, sessionManager);
		}
		// 扫描未携带tuid，model的二维码，将处理权还给handle
		this.logger.info(wxMessage.getFromUser() + "扫描了普通的二维码或者搜索关注");
		return null;
	}

}

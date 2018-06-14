package com.changan.icar.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.wechat.service.impl.FunctionService;
import com.changan.icar.wechat.service.impl.WeixinService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage.WxArticle;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Controller
@RequestMapping(value = "pages")
@Configuration
public class WxPortalController {
	@Autowired
	private WeixinService weixinService;

	@Autowired
	private FunctionService functionService;

	@Value("${domain}")
	private String domain;
	@Value("${protocol}")
	private String protocol;

	@ResponseBody
	@RequestMapping(value = "/getConfig.do", method = RequestMethod.GET)
	public WxJsapiSignature getConfig(@RequestParam String url) throws WxErrorException, Exception {
		String ticket = weixinService.getJsapiTicket();
		LogUtils.info("请求的jsApi ticket：" + ticket);
		WxJsapiSignature data = weixinService.createJsapiSignature(url);
		return data;
	}

	@RequestMapping(value = "/deviceManage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView deviceList(HttpServletRequest request) throws WxErrorException, Exception {
		// 通过oauth获取openId
		ModelAndView mv = new ModelAndView("/WEB-INF/pages/device_manage.jsp");
		String code = request.getParameter("code");
		addOpenId(code, mv);
		return mv;
	}

	private String getHost() {
		return protocol + "://" + domain;
	}

	@RequestMapping(value = "/deviceLocation.do", method = { RequestMethod.GET })
	public ModelAndView deviceLocation(HttpServletRequest request) throws WxErrorException, Exception {
		// 通过oauth获取openId
		ModelAndView mv = new ModelAndView("/WEB-INF/pages/device_location.jsp");
		String code = request.getParameter("code");
		WxMpOAuth2AccessToken token = weixinService.oauth2getAccessToken(code);
		String openId = token.getOpenId();
		mv.addObject("openId", openId);
		return mv;
	}

	@RequestMapping(value = "/wyjr.do", method = RequestMethod.GET)
	public ModelAndView wyjr(HttpServletRequest request) throws WxErrorException, Exception {

		ModelAndView mv = new ModelAndView("/WEB-INF/pages/wyjr.jsp");
		String code = request.getParameter("code");
		addOpenId(code, mv);
		return mv;

	}

	@RequestMapping(value = "/gotoWyjrSelectLocation.do", method = RequestMethod.GET)
	public String wyjrSelectLocation(@RequestParam String pickCode) {
		LogUtils.info("**************pickCode:" + pickCode);
		String url = weixinService.oauth2buildAuthorizationUrl(getHost() + "/icar-wechat/pages/wyjrSelectLocation.do",
				WxConsts.OAUTH2_SCOPE_USER_INFO, pickCode);
		LogUtils.info("*******url:" + url);
		return "redirect:" + url;
	}

	@RequestMapping(value = "/wyjrSelectLocation.do", method = RequestMethod.GET)
	public ModelAndView wyjrSelectLocation(HttpServletRequest request) throws WxErrorException, Exception {
		ModelAndView mv = new ModelAndView("/WEB-INF/pages/wyjr_select_location.jsp");
		String code = request.getParameter("code");
		String pickCode = request.getParameter("state");
		LogUtils.info("pickCode:" + pickCode);
		mv.addObject("pickCode", pickCode);
		addUserInfo(code, mv);
		return mv;
	}

	@RequestMapping(value = "/gotoZrjwConfirm.do", method = RequestMethod.GET)
	public String gotoZrjwConfirm(@RequestParam String pickCode) {
		LogUtils.info("**************pickCode:" + pickCode);
		String url = weixinService.oauth2buildAuthorizationUrl(getHost() + "/icar-wechat/pages/zrjwConfirm.do",
				WxConsts.OAUTH2_SCOPE_USER_INFO, pickCode);
		LogUtils.info("*******url:" + url);
		return "redirect:" + url;
	}

	@RequestMapping(value = "/wyjrConfirm.do", method = RequestMethod.GET)
	public ModelAndView wyjrConfirm(HttpServletRequest request) throws WxErrorException, Exception {
		ModelAndView mv = new ModelAndView("/WEB-INF/pages/wyjr_confirm.jsp");
		String code = request.getParameter("code");
		String pickCode = request.getParameter("state");
		LogUtils.info("pickCode:" + pickCode);
		mv.addObject("pickCode", pickCode);
		addOpenId(code, mv);
		return mv;

	}

	@RequestMapping(value = "/zrjwSelectLocation.do", method = RequestMethod.GET)
	public ModelAndView zrjwSelectLocation(HttpServletRequest request) throws WxErrorException, Exception {
		ModelAndView mv = new ModelAndView("/WEB-INF/pages/zrjw_select_location.jsp");
		String code = request.getParameter("code");
		String pickCode = request.getParameter("state");
		LogUtils.info("pickCode:" + pickCode);
		mv.addObject("pickCode", pickCode);
		addUserInfo(code, mv);
		return mv;

	}

	@RequestMapping(value = "/zrjwConfirm.do", method = RequestMethod.GET)
	public ModelAndView zrjwConfirm(HttpServletRequest request) throws WxErrorException, Exception {
		ModelAndView mv = new ModelAndView("/WEB-INF/pages/zrjw_confirm.jsp");
		String code = request.getParameter("code");
		String pickCode = request.getParameter("state");
		LogUtils.info("pickCode:" + pickCode);
		mv.addObject("pickCode", pickCode);
		addUserInfo(code, mv);
		return mv;

	}

	@RequestMapping(value = "/privateOrdering.do", method = RequestMethod.GET)
	public ModelAndView privateOrdering(HttpServletRequest request) throws WxErrorException, Exception {
		ModelAndView mv = new ModelAndView("/WEB-INF/pages/private_ordering.jsp");
		String code = request.getParameter("code");
		addOpenId(code, mv);
		return mv;

	}

	@RequestMapping(value = "/privateOrderingHome", method = RequestMethod.GET)
	public ModelAndView privateOrderingHome(HttpServletRequest request) throws WxErrorException, Exception {
		ModelAndView mv = new ModelAndView("/WEB-INF/pages/private_ordering_pages/home.html");
		return mv;

	}

	@RequestMapping(value = "/privateOrderingNewIdea", method = RequestMethod.GET)
	public ModelAndView privateOrderingNewIdea(HttpServletRequest request) throws WxErrorException, Exception {
		ModelAndView mv = new ModelAndView("/WEB-INF/pages/private_ordering_pages/newIdea.html");
		return mv;

	}

	@ResponseBody
	@RequestMapping(value = "/wyjrLocationBack.do", method = RequestMethod.GET)
	public Result wyjrLocationBack(@RequestParam String openId, @RequestParam String pickCode,
			@RequestParam String nickName, @RequestParam String headImgUrl, @RequestParam Double latitude,
			@RequestParam Double longitude) throws WxErrorException, Exception {
		try {
			LogUtils.info("openId:" + openId + ",pickCode:" + pickCode);
			Result result = functionService.updateOperationByMsgId(openId, pickCode, nickName, headImgUrl, longitude,
					latitude);
			if (result.getCode() == 200) {// 更新成功，发送消息给微信账号通知
				String title = nickName + "已确认位置";
				String description = "你的小伙伴" + nickName + "已确认位置，快点击该链接查看位置吧";
				String redirectURI = getHost() + "/icar-wechat/pages/wyjrConfirm.do";
				String oauthUrl = weixinService.oauth2buildAuthorizationUrl(redirectURI, WxConsts.OAUTH2_SCOPE_BASE,
						pickCode);
				LogUtils.info("send to:" + (String) result.getData());
				WxArticle article = buildArticle(title, description, headImgUrl, oauthUrl);
				LogUtils.info("article:" + article);
				WxMpKefuMessage message = buildKefuMessage((String) result.getData(), article);
				LogUtils.info("message:" + message.toJson());
				sendKefuMessage(message);
				return new Result().ok(null);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.error("发生异常:" + e.getMessage());
			return new Result().serverError("服务器异常");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/zrjwBack.do", method = RequestMethod.GET)
	public Result zrjwBack(@RequestParam String openId, @RequestParam String pickCode,
			@RequestParam(required = false) String tuid, @RequestParam String nickName, @RequestParam String headImgUrl,
			@RequestParam Boolean isAgree) throws WxErrorException, Exception {
		try {
			Result result = functionService.updateOperationStatusByMsgId(openId, pickCode, tuid);
			String title = "";
			String description = "";
			if (isAgree) {
				title = nickName + "同意了您的请求，正在前往接你的路上！";
				description = "稍等，" + nickName + "已经来咯！";
			} else {
				title = nickName + "拒绝了您的请求，要不打电话联系下？";
				description = "互相理解，" + nickName + "可能有事，打个taxi吧！";
			}
			String redirectURI = getHost() + "/icar-wechat/pages/newFunc.html";
			String oauthUrl = weixinService.oauth2buildAuthorizationUrl(redirectURI, WxConsts.OAUTH2_SCOPE_BASE,
					pickCode);
			sendKefuMessage(buildKefuMessage((String) result.getData(),
					buildArticle(title, description, headImgUrl, oauthUrl)));
			return new Result().ok(null);
		} catch (Exception e) {
			LogUtils.error("错误：msg:" + e.getMessage());
			return new Result().serverError("服务器异常");
		}
	}

	private void sendKefuMessage(WxMpKefuMessage message) throws WxErrorException {
		if (message == null) {
			LogUtils.error("客服消息为空");
		}
		LogUtils.info("客服消息:" + message.toJson());
		WxMpKefuService kefuService = weixinService.getKefuService();
		kefuService.sendKefuMessage(message);
	}

	private WxMpKefuMessage buildKefuMessage(String to, WxArticle article) {
		LogUtils.info("组装客服消息,to:" + to + "article:" + article);
		return WxMpKefuMessage.NEWS()

				.toUser(to)

				.addArticle(article)

				.build();
	}

	private WxArticle buildArticle(String title, String description, String picUrl, String url) {
		WxArticle article = new WxArticle();
		article.setTitle(title);
		article.setDescription(description);
		article.setPicUrl(picUrl);
		article.setUrl(url);
		return article;
	}

	private void addUserInfo(String code, ModelAndView mv) throws WxErrorException {
		WxMpOAuth2AccessToken token = weixinService.oauth2getAccessToken(code);
		String openId = token.getOpenId();
		WxMpUser userInfo = weixinService.oauth2getUserInfo(token, null);
		mv.addObject("openId", openId);
		mv.addObject("nickName", userInfo.getNickname());
		mv.addObject("headImgUrl", userInfo.getHeadImgUrl());
	}

	private void addOpenId(String code, ModelAndView mv) throws WxErrorException {
		WxMpOAuth2AccessToken token = weixinService.oauth2getAccessToken(code);
		String openId = token.getOpenId();
		mv.addObject("openId", openId);
	}

}

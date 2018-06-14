package com.changan.icar.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.changan.icar.wechat.Config;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;

@RestController
@RequestMapping("/wechat/menu")
public class WxMenuController implements WxMpMenuService {

	@Autowired
	private WxMpService wxService;

	/**
	 * 自定义菜单创建接口
	 * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN
	 * 如果要创建个性化菜单，请设置matchrule属性
	 * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
	 *
	 * @param menu
	 * @return 如果是个性化菜单，则返回menuid，否则返回null
	 */
	@Override
	@PostMapping("/create")
	public String menuCreate(@RequestBody WxMenu menu) throws WxErrorException {
		return this.wxService.getMenuService().menuCreate(menu);
	}

	// @GetMapping("/create")
	// public String menuCreateSample() throws WxErrorException {
	// // 创建自定义菜单
	// WxMenu menu = new WxMenu();
	// // 创建自定义菜单按钮列表
	// List<WxMenuButton> menuButtonList = new ArrayList<>();
	// // 创建自定义菜单按钮
	// WxMenuButton locationMenuButton = new WxMenuButton();
	// locationMenuButton.setName("位置服务");
	// // TODO 此处硬编码
	// locationMenuButton.setType("click");
	// locationMenuButton.setKey("location_service");
	//
	// // 创建“位置服务的”子菜单列表
	// List<WxMenuButton> locationSubButtonList = new ArrayList<>();
	//
	// // 创建“我要接人”按钮
	// WxMenuButton wyjrButton = new WxMenuButton();
	// wyjrButton.setName("我要接人");
	// wyjrButton.setType("view");
	// String wyjrUrl = domain + "/icar-wechatmp/function/wyjr.do";
	// String wyjrUrlOauthUrl = wxService.oauth2buildAuthorizationUrl(wyjrUrl,
	// WxConsts.OAUTH2_SCOPE_BASE, null);
	// wyjrButton.setUrl(wyjrUrlOauthUrl);
	//
	// // 加入到“位置服务”的子菜单
	// locationSubButtonList.add(wyjrButton);
	//
	// // 创建“找人接我”按钮
	// WxMenuButton zrjwButton = new WxMenuButton();
	// zrjwButton.setName("找人接我");
	// zrjwButton.setType("view");
	// String zrjwUrl = domain +
	// "/icar-wechatmp/function/zrjwSelectLocation.do";
	// String zrjwUrlOauthUrl = wxService.oauth2buildAuthorizationUrl(zrjwUrl,
	// WxConsts.OAUTH2_SCOPE_USER_INFO, null);
	// zrjwButton.setUrl(zrjwUrlOauthUrl);
	// zrjwButton.setKey("zrjw");
	//
	// // 加入到“位置服务”的子菜单
	// locationSubButtonList.add(zrjwButton);
	//
	// // 创建“快速导航”按钮
	// WxMenuButton naviButton = new WxMenuButton();
	// naviButton.setName("快速导航");
	// naviButton.setType("location_select");
	// naviButton.setKey("quick_navi");
	//
	// // 加入到“位置服务”的子菜单
	// locationSubButtonList.add(naviButton);
	//
	// // 将子菜单列表设置到父按钮“位置服务中”
	// locationMenuButton.setSubButtons(locationSubButtonList);
	//
	// // locationMenuButton将button放入menu中
	// menuButtonList.add(locationMenuButton);
	//
	// // 新建设备控制menuButton
	// WxMenuButton operationButton = new WxMenuButton();
	// operationButton.setName("控制设备");
	// operationButton.setType("click");
	// operationButton.setKey("operation");
	// // 新建测试二级按钮列表
	// List<WxMenuButton> operationSubButtonList = new ArrayList<>();
	//
	// // 获取车辆位置
	// WxMenuButton testLocationSelectBtn = new WxMenuButton();
	// testLocationSelectBtn.setName("获取车辆位置");
	// testLocationSelectBtn.setType("view");
	// String deviceLocationUrl = domain +
	// "/icar-wechatmp/device/deviceLocation.do";
	// String deviceLocationOauthUrl =
	// wxService.oauth2buildAuthorizationUrl(deviceLocationUrl,
	// WxConsts.OAUTH2_SCOPE_BASE, null);
	// // testLocationSelectBtn.setKey("get_location");
	// testLocationSelectBtn.setUrl(deviceLocationOauthUrl);
	// // 将车辆位置加入到父按钮中
	// operationSubButtonList.add(testLocationSelectBtn);
	//
	// // 将测试的子菜单加入到父按钮中
	// operationButton.setSubButtons(operationSubButtonList);
	//
	// // 将测试菜单按钮加入到列表中
	// menuButtonList.add(operationButton);
	//
	// // 新建管理中心
	// WxMenuButton manageCenterBtn = new WxMenuButton();
	// manageCenterBtn.setName("用户中心");
	// manageCenterBtn.setType("click");
	//
	// WxMenuButton deviceManageBtn = new WxMenuButton();
	// deviceManageBtn.setName("设备管理");
	// deviceManageBtn.setKey("deviceManage");
	// deviceManageBtn.setType("view");
	// // 构造授权网页
	// String deviceManageUrl = domain +
	// "/icar-wechatmp/device/deviceManage.do";
	// String oauthUrl = wxService.oauth2buildAuthorizationUrl(deviceManageUrl,
	// WxConsts.OAUTH2_SCOPE_BASE, null);
	// deviceManageBtn.setUrl(oauthUrl);
	//
	// WxMenuButton newAccountBtn = new WxMenuButton();
	// newAccountBtn.setName("新建账号");
	// newAccountBtn.setType("click");
	// newAccountBtn.setKey("new_account");
	//
	// WxMenuButton privateOrderBtn = new WxMenuButton();
	// privateOrderBtn.setName("粉丝至上");
	// privateOrderBtn.setType("view");
	// privateOrderBtn.setKey("private_ordering");
	//
	// // 构造授权网页
	// String privateOrderUrl = domain +
	// "/icar-wechatmp/function/privateOrdering.do";
	// String privateOrderOauthUrl =
	// wxService.oauth2buildAuthorizationUrl(privateOrderUrl,
	// WxConsts.OAUTH2_SCOPE_BASE,
	// null);
	// privateOrderBtn.setUrl(privateOrderOauthUrl);
	//
	// List<WxMenuButton> manageCenterSubBtnList = new ArrayList<>();
	// manageCenterSubBtnList.add(deviceManageBtn);
	// manageCenterSubBtnList.add(newAccountBtn);
	// manageCenterSubBtnList.add(privateOrderBtn);
	//
	// // 设置子按钮
	// manageCenterBtn.setSubButtons(manageCenterSubBtnList);
	// // 将管理中心菜单按钮加入到menuList
	// menuButtonList.add(manageCenterBtn);
	// // 将按钮列表添加到菜单中
	// menu.setButtons(menuButtonList);
	//
	// return this.wxService.getMenuService().menuCreate(menu);
	// }

	@GetMapping("/create")
	public String menuCreateSample() throws WxErrorException {
		// 创建自定义菜单
		Config config = new Config();
		return this.wxService.getMenuService().menuCreate(config.getMenu(wxService));
	}

	/**
	 * 自定义菜单创建接口 详情请见：
	 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN
	 * 如果要创建个性化菜单，请设置matchrule属性
	 * 详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
	 *
	 * @param json
	 * @return 如果是个性化菜单，则返回menuid，否则返回null
	 */
	@Override
	@GetMapping("/create/{json}")
	public String menuCreate(@PathVariable String json) throws WxErrorException {
		return this.wxService.getMenuService().menuCreate(json);
	}

	/**
	 * 自定义菜单删除接口 详情请见:
	 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141015&token=&lang=zh_CN
	 */
	@Override
	@GetMapping("/delete")
	public void menuDelete() throws WxErrorException {
		this.wxService.getMenuService().menuDelete();
	}

	/**
	 * 删除个性化菜单接口 详情请见:
	 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN
	 *
	 * @param menuId
	 *            个性化菜单的menuid
	 */
	@Override
	@GetMapping("/delete/{menuId}")
	public void menuDelete(@PathVariable String menuId) throws WxErrorException {
		this.wxService.getMenuService().menuDelete(menuId);
	}

	/**
	 * <pre>
	 * 自定义菜单查询接口
	 * 详情请见： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141014&token=&lang=zh_CN
	 * </pre>
	 */
	@Override
	@GetMapping("/get")
	public WxMpMenu menuGet() throws WxErrorException {
		return this.wxService.getMenuService().menuGet();
	}

	/**
	 * 测试个性化菜单匹配结果 详情请见:
	 * http://mp.weixin.qq.com/wiki/0/c48ccd12b69ae023159b4bfaa7c39c20.html
	 *
	 * @param userid
	 *            可以是粉丝的OpenID，也可以是粉丝的微信号。
	 */
	@Override
	@GetMapping("/menuTryMatch/{userid}")
	public WxMenu menuTryMatch(@PathVariable String userid) throws WxErrorException {
		return this.wxService.getMenuService().menuTryMatch(userid);
	}

	/**
	 * 获取自定义菜单配置接口
	 * 本接口将会提供公众号当前使用的自定义菜单的配置，如果公众号是通过API调用设置的菜单，则返回菜单的开发配置，而如果公众号是在公众平台官网通过网站功能发布菜单，则本接口返回运营者设置的菜单配置。
	 * 请注意：
	 * 1、第三方平台开发者可以通过本接口，在旗下公众号将业务授权给你后，立即通过本接口检测公众号的自定义菜单配置，并通过接口再次给公众号设置好自动回复规则，以提升公众号运营者的业务体验。
	 * 2、本接口与自定义菜单查询接口的不同之处在于，本接口无论公众号的接口是如何设置的，都能查询到接口，而自定义菜单查询接口则仅能查询到使用API设置的菜单配置。
	 * 3、认证/未认证的服务号/订阅号，以及接口测试号，均拥有该接口权限。 4、从第三方平台的公众号登录授权机制上来说，该接口从属于消息与菜单权限集。
	 * 5、本接口中返回的图片/语音/视频为临时素材（临时素材每次获取都不同，3天内有效，通过素材管理-获取临时素材接口来获取这些素材），本接口返回的图文消息为永久素材素材（通过素材管理-获取永久素材接口来获取这些素材）。
	 * 接口调用请求说明: http请求方式: GET（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN
	 */
	@Override
	@GetMapping("/getSelfMenuInfo")
	public WxMpGetSelfMenuInfoResult getSelfMenuInfo() throws WxErrorException {
		return this.wxService.getMenuService().getSelfMenuInfo();
	}
}

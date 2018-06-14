package com.changan.icar.wechat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.changan.icar.comm.util.StringUtils;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.mp.api.WxMpService;

@Configuration
public class Config {
	private String protocol = "http";
	private String domain = "icar.natapp1.cc";

	private void buildOauthUrlIfExist(WxMenuButton button, WxMpService wxService) {
		String url = button.getUrl();
		if (StringUtils.isNull(url)) {
			return;
		}
		String oauthUrl = wxService.oauth2buildAuthorizationUrl(buildFullUrl(url), WxConsts.OAUTH2_SCOPE_USER_INFO,
				null);
		button.setUrl(oauthUrl);
	}

	private String buildFullUrl(String url) {
		return protocol + "://" + domain + url;
	}

	public WxMenu getMenu(WxMpService wxService) {
		Config config = new Config();
		config.setDomain(domain);
		config.setProtocol(protocol);
		List<WxMenuButton> buttons = new ArrayList<>();
		WxMenu menu = new WxMenu();
		buttons.add(locationButtons());
		buttons.add(controlButtons());
		buttons.add(userCenterButtons());
		menu.setButtons(buttons);
		iterButtons(buttons, wxService);
		return menu;
	}

	private void iterButtons(List<WxMenuButton> buttons, WxMpService wxService) {
		for (WxMenuButton button : buttons) {
			buildOauthUrlIfExist(button, wxService);
			List<WxMenuButton> subButtons = button.getSubButtons();
			if (subButtons != null) {
				for (WxMenuButton wxMenuButton : subButtons) {
					buildOauthUrlIfExist(wxMenuButton, wxService);
				}
			}
		}
	}

	private WxMenuButton userCenterButtons() {
		// 新建管理中心
		WxMenuButton button = new WxMenuButton();
		button.setName("用户中心");
		button.setType("click");
		List<WxMenuButton> subButtons = new ArrayList<>();

		WxMenuButton deviceManageBtn = new WxMenuButton();
		deviceManageBtn.setName("设备管理");
		deviceManageBtn.setKey("deviceManage");
		deviceManageBtn.setType("view");
		String deviceManageUrl = "/icar-wechat/pages/deviceManage.do";
		deviceManageBtn.setUrl(deviceManageUrl);

		subButtons.add(deviceManageBtn);

		WxMenuButton privateOrderBtn = new WxMenuButton();
		privateOrderBtn.setName("粉丝至上");
		privateOrderBtn.setType("view");
		privateOrderBtn.setKey("private_ordering");
		String privateOrderUrl = "/icar-wechat/pages/privateOrdering.do";
		privateOrderBtn.setUrl(privateOrderUrl);

		subButtons.add(privateOrderBtn);

		button.setSubButtons(subButtons);
		return button;
	}

	private WxMenuButton controlButtons() {
		// 新建设备控制menuButton
		WxMenuButton button = new WxMenuButton();
		button.setName("控制设备");
		button.setType("click");
		button.setKey("operation");
		// 新建测试二级按钮列表
		List<WxMenuButton> subButtons = new ArrayList<>();
		// 获取车辆位置
		WxMenuButton locationSelectBtn = new WxMenuButton();
		locationSelectBtn.setName("获取车辆位置");
		locationSelectBtn.setType("view");
		String url = "/icar-wechat/pages/deviceLocation.do";
		locationSelectBtn.setUrl(url);
		// 将车辆位置加入到父按钮中
		subButtons.add(locationSelectBtn);
		// 将测试的子菜单加入到父按钮中
		button.setSubButtons(subButtons);
		return button;
	}

	private WxMenuButton locationButtons() {
		WxMenuButton button = new WxMenuButton();
		button.setName("位置服务");
		button.setType("click");
		button.setKey("location_service");

		// 创建“位置服务的”子菜单列表
		List<WxMenuButton> subButtons = new ArrayList<>();
		// 创建“我要接人”按钮
		WxMenuButton wyjrButton = new WxMenuButton();
		wyjrButton.setName("我要接人");
		wyjrButton.setType("view");
		String wyjrUrl = "/icar-wechat/pages/wyjr.do";
		wyjrButton.setUrl(wyjrUrl);
		// 加入到“位置服务”的子菜单
		subButtons.add(wyjrButton);
		// 创建“找人接我”按钮
		WxMenuButton zrjwButton = new WxMenuButton();
		zrjwButton.setName("找人接我");
		zrjwButton.setType("view");
		String zrjwUrl = "/icar-wechat/pages/zrjwSelectLocation.do";
		zrjwButton.setUrl(zrjwUrl);
		zrjwButton.setKey("zrjw");
		// 加入到“位置服务”的子菜单
		subButtons.add(zrjwButton);
		// 创建“快速导航”按钮
		WxMenuButton naviButton = new WxMenuButton();
		naviButton.setName("快速导航");
		naviButton.setType("location_select");
		naviButton.setKey("quick_navi");
		// 加入到“位置服务”的子菜单
		subButtons.add(naviButton);
		// 将子菜单列表设置到父按钮“位置服务中”
		button.setSubButtons(subButtons);
		return button;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public String toString() {
		return "Config [protocol=" + protocol + ", domain=" + domain + "]";
	}

}

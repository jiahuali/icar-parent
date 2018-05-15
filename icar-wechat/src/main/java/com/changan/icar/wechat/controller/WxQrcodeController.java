package com.changan.icar.wechat.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpQrcodeService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

@Controller
@RequestMapping(value = "qrcode")
public class WxQrcodeController {

	@Autowired
	private WxMpService wxService;

	@GetMapping("/getQrcode")
	public void getQrcode(@RequestParam String token, @RequestParam String tuid, @RequestParam String model,
			@RequestParam(required = false) Integer type, HttpServletResponse resp)
			throws WxErrorException, IOException, Exception {

		// TODO 判断是否为空
		String content = "bind:" + tuid + "," + model;
		if (type != null)
			content += "," + type;

		WxMpQrcodeService wxQrcodeService = wxService.getQrcodeService();
		WxMpQrCodeTicket ticket = wxQrcodeService.qrCodeCreateLastTicket(content);

		File qrCodePicture = wxQrcodeService.qrCodePicture(ticket);
		BufferedImage bufferedImage = ImageIO.read(qrCodePicture);

		OutputStream os = resp.getOutputStream();

		ImageIO.write(bufferedImage, "png", os);

	}

}

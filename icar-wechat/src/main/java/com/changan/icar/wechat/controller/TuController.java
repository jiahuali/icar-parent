package com.changan.icar.wechat.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.wechat.OnlineDeviceMap;
import com.changan.icar.wechat.service.TuService;

@RestController
@RequestMapping("tu")
public class TuController {

	@Autowired
	private TuService tuService;
	@Autowired
	private OnlineDeviceMap onlineDeviceMap;

	@PostMapping("/updateDeviceByTuid")
	public Result updateDeviceByTuid(String tuid, BigDecimal oilAmount, BigDecimal speed, Float heading,
			BigDecimal altitude, BigDecimal longitude, BigDecimal latitude) {
		// 上传位置信息,间隔n s
		// oil_amount,longitude,latitude,speed,altitude,heading
		return tuService.updateTuByTuid(tuid, oilAmount, speed, heading, altitude, longitude, latitude);
	}

	@PostMapping("/tuStart")
	public Result updateDeviceStatusByTuid(String tuid, String status) {
		// 接收消息后将设备开机
		if (onlineDeviceMap.addOnlineDeviceByTuid(tuid, null)) {
			return new Result().ok(true);
		}
		return new Result().serverError("开机失败");

	}

}

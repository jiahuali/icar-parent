package com.changan.icar.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.wechat.service.DeviceService;

@RestController
@RequestMapping("device")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@GetMapping("/listDeviceByOpenId")
	public Result listDeviceByOpenId(String openId) {
		return deviceService.listDeviceByOpenId(openId);
	}

	@PostMapping("/updateDeviceNameByTuidAndOpenId")
	public Result updateDeviceNameByTuidAndOpenId(String tuid, String openId, String name) {
		return deviceService.updateDeviceNameByTuidAndOpenId(tuid, openId, name);
	}

	@PostMapping("/updateDeviceAsDefaultByTuidAndOpenId")
	public Result updateDeviceAsDefaultByTuidAndOpenId(String openId, String tuid) {
		return deviceService.updateDeviceAsDefaultByTuidAndOpenId(openId, tuid);
	}

	@PostMapping("/deleteDeviceByTuidAndOpenId")
	public Result deleteDeviceByTuidAndOpenId(String openId, String tuid) {
		return deviceService.deleteDeviceByTuidAndOpenId(openId, tuid);
	}

	@GetMapping("/getDefaultDeviceByOpenId")
	public Result getDefaultDeviceByOpenId(String openId) {
		return deviceService.getDefaultDeviceByOpenId(openId);
	}
}

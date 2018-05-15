package com.changan.icar.wechat.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Device;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.wechat.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Reference(version = "1.0.0")
	private com.changan.icar.comm.service.DeviceService deviceService;

	@Override
	public Result addDevice(String tuid, String uuid, String model, Device device) {
		LogUtils.info("请求添加车辆:tuid:" + tuid + ",uuid:" + uuid + ",model:" + model);
		if (StringUtils.isNull(tuid, uuid, model)) {
			return new Result().paramsNull("参数不能为空");
		}
		return deviceService.addDeviceByAccountUuid(uuid, tuid, model, device);
	}

}

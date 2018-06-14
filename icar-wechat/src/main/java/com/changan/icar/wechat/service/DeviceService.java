package com.changan.icar.wechat.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Device;

public interface DeviceService {
	Result addDevice(String tuid, String uuid, String model, Device device);

	Result listDeviceByOpenId(String openId);

	Result updateDeviceNameByTuidAndOpenId(String tuid, String openId, String name);

	Result updateDeviceAsDefaultByTuidAndOpenId(String openId, String tuid);

	Result updateDeviceByTuid(String tuid, Device device);

	Result deleteDeviceByTuidAndOpenId(String openId, String tuid);

	Result getDefaultDeviceByOpenId(String openId);

}

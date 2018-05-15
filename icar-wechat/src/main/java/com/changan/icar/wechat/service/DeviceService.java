package com.changan.icar.wechat.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Device;

public interface DeviceService {
	Result addDevice(String tuid, String uuid, String model, Device device);
}

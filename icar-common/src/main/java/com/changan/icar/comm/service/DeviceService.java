package com.changan.icar.comm.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Device;
import com.changan.icar.comm.po.DeviceExample;

public interface DeviceService {

	Result listDevice(Integer pageSize, Integer pages);

	Result getDeviceByTuid(String tuid);

	Result updateDeviceByTuid(String tuid, Device device);

	Result addDeviceByAccountUuid(String uuid, String tuid, String model, Device device);

	Result deleteDeviceByTuid(String tuid);

	Result listDeviceByExample(DeviceExample example, Integer pageSize, Integer pages);
}

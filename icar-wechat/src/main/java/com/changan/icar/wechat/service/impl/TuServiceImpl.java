package com.changan.icar.wechat.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Device;
import com.changan.icar.comm.service.DeviceService;
import com.changan.icar.comm.service.LocationService;
import com.changan.icar.comm.service.TravelService;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.wechat.service.TuService;

@Service
public class TuServiceImpl implements TuService {
	@Reference(version = "1.0.0")
	private DeviceService deviceService;
	@Reference(version = "1.0.0")
	private LocationService locationService;
	@Reference(version = "1.0.0")
	private TravelService travelService;

	@Override
	public Result updateTuByTuid(String tuid, BigDecimal oilAmount, BigDecimal speed, Float heading,
			BigDecimal altitude, BigDecimal longitude, BigDecimal latitude) {
		if (StringUtils.isNull(tuid) || oilAmount == null || speed == null || heading == null || altitude == null
				|| longitude == null || latitude == null) {
			return new Result().paramError("参数不能为空");
		}
		Device device = new Device();
		device.setAltitude(altitude);
		device.setHeading(heading);
		device.setLatitude(latitude);
		device.setLongitude(longitude);
		device.setSpeed(speed);
		device.setOilAmount(oilAmount);
		device.setTuid(tuid);
		deviceService.updateDeviceByTuid(tuid, device);
		return null;
	}

}

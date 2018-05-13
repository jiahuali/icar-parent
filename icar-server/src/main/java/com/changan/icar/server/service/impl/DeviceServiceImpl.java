package com.changan.icar.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Device;
import com.changan.icar.comm.po.DeviceExample;
import com.changan.icar.comm.service.DeviceService;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.server.dao.DeviceMapper;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceMapper deviceMapper;

	@Override
	public Result listDevice(Integer pageSize, Integer pages) {
		return listDeviceByCriteria(null, pageSize, pages);
	}

	@Override
	public Result getDeviceByTuid(String tuid) {
		LogUtils.info("正在根据tuid查询车辆，tuid:" + tuid);
		if (StringUtils.isNull(tuid)) {
			LogUtils.error("根据tuid查询车辆时，tuid不能为空");
			return new Result().paramsNull("根据tuid查询车辆时，tuid不能为空");
		}
		try {
			Device device = deviceMapper.selectByPrimaryKey(tuid);
			if (device == null) {
				LogUtils.info("未查询到device,tuid:" + tuid);
				return new Result().noRecord("未查询到device");
			}
			LogUtils.info("查询到的device:" + device.toString());
			return new Result().ok(device);
		} catch (Exception e) {
			LogUtils.error("查询device发生异常,msg:" + e.getMessage());
			return new Result().serverError("查询device失败");
		}

	}

	@Override
	public Result updateDeviceByTuid(String tuid, Device device) {
		LogUtils.info("请求更新device，：" + tuid);
		if (StringUtils.isNull(tuid) || device == null) {
			LogUtils.error("更新时必须携带tuid以及要更新的参数");
			return new Result().paramsNull("更新时必须携带tuid以及要更新的参数");
		}
		device.setTuid(tuid);
		LogUtils.info("要更新的device:" + device.toString());
		try {
			int rows = deviceMapper.updateByPrimaryKeySelective(device);
			if (rows == 1) {
				LogUtils.info("更新成功:" + device.toString());
				return new Result().ok(null);
			}
		} catch (Exception e) {
			LogUtils.error("更新device时发生异常,msg:" + e.getMessage());
		}
		LogUtils.error("更新失败：" + device.toString());
		return new Result().serverError("更新device时发生错误," + device.toString());
	}

	@Override
	public Result addDeviceByAccountUuid(String uuid, String tuid, String model, Device device) {
		LogUtils.info("请求添加车辆,uuid:" + uuid + ",tuid:" + tuid + ",model:" + model);
		if (StringUtils.isNull(uuid, tuid, model)) {
			LogUtils.error("添加车辆必须有uuid和tuid,model");
			return new Result().paramsNull("添加车辆必须有uuid和tuid,model");
		}
		if (device == null) {
			device = new Device();
		}
		// 多重保障
		device.setTuid(tuid);
		device.setUuid(uuid);
		device.setModel(model);

		LogUtils.info("插入device," + device.toString());
		try {
			int rows = deviceMapper.insertSelective(device);
			if (rows > 0) {
				LogUtils.info("插入device成功");
				return new Result().ok(device);
			} else {
				LogUtils.info("插入account失败");
			}
		} catch (Exception e) {
			LogUtils.info("插入device发生异常,信息:" + e.getMessage());
		}
		return new Result().serverError("添加device失败");
	}

	@Override
	public Result deleteDeviceByTuid(String tuid) {
		LogUtils.info("请求删除车辆，tuid:" + tuid);
		if (StringUtils.isNull(tuid)) {
			LogUtils.error("请求删除车辆必须有tuid");
			return new Result().paramsNull("删除车辆必须提供tuid");
		}
		try {
			int rows = deviceMapper.deleteByPrimaryKey(tuid);
			if (rows == 1) {
				return new Result().ok(null);
			}
		} catch (Exception e) {
			return new Result().serverError("发生异常，msg:" + e.getMessage());
		}
		return new Result().serverError("删除时发生未知异常");
	}

	@Override
	public Result listDeviceByCriteria(DeviceExample example, Integer pageSize, Integer pages) {
		if (example == null) {
			// 查询条件为空，不予返回
			example = new DeviceExample();
		}
		LogUtils.info("查询DeviceList，" + example);
		try {
			List<Device> deviceList = deviceMapper.selectByExample(example);
			if (deviceList == null || deviceList.size() == 0) {
				return new Result().noRecord("未查询到result");
			}
			return new Result().ok(deviceList);
		} catch (Exception e) {
			LogUtils.error("listDeviceByCriteria发生异常，msg:" + e.getMessage());
			return new Result().serverError("获取设备列表异常");
		}
	}

}

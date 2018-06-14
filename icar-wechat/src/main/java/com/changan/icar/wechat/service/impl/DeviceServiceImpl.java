package com.changan.icar.wechat.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.changan.icar.comm.dto.IcarConst;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;
import com.changan.icar.comm.po.Device;
import com.changan.icar.comm.po.DeviceExample;
import com.changan.icar.comm.service.AccountService;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.wechat.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Reference(version = "1.0.0")
	private com.changan.icar.comm.service.DeviceService deviceService;
	@Reference(version = "1.0.0")
	private AccountService accountService;

	@Override
	public Result addDevice(String tuid, String uuid, String model, Device device) {
		LogUtils.info("请求添加车辆:tuid:" + tuid + ",uuid:" + uuid + ",model:" + model);
		if (StringUtils.isNull(tuid, uuid, model)) {
			return new Result().paramsNull("参数不能为空");
		}
		if (StringUtils.isNull(device.getName())) {// 车辆默认名为车型代号
			device.setName(model);
		}
		try {
			return deviceService.addDeviceByAccountUuid(uuid, tuid, model, device);
		} catch (Exception e) {
			LogUtils.error("添加车辆时，服务器异常，" + e.getMessage());
			return new Result().serverError("服务器异常");
		}

	}

	@Override
	public Result listDeviceByOpenId(String openId) {
		LogUtils.info("请求list车辆:openId:" + openId);
		if (StringUtils.isNull(openId)) {
			return new Result().paramsNull("参数不能为空");
		}
		DeviceExample example = new DeviceExample();
		example.createCriteria().andUuidEqualTo(openId);
		try {
			return deviceService.listDeviceByExample(example, 0, 0);
		} catch (Exception e) {
			LogUtils.error("list车辆时，服务器异常，" + e.getMessage());
			return new Result().serverError("服务器异常");
		}

	}

	@Override
	public Result updateDeviceNameByTuidAndOpenId(String tuid, String openId, String name) {
		LogUtils.info("请求更新设备名称,tuid:" + tuid + ",openid:" + openId + ",name:" + name);
		if (StringUtils.isNull(tuid, openId, name)) {
			return new Result().paramsNull("参数不能为空");
		}
		try {
			if (!isTuidMatchOpenId(openId, tuid)) {
				return new Result().serverError("用户与设备不匹配");
			}
			Device device = new Device();
			device.setTuid(tuid);
			device.setUuid(openId);
			device.setName(name);
			return deviceService.updateDeviceByTuid(tuid, device);
		} catch (Exception e) {
			LogUtils.error("更新设备名称发生异常," + e.getMessage());
			return new Result().serverError("服务器异常");
		}

	}

	@Override
	public Result updateDeviceAsDefaultByTuidAndOpenId(String openId, String tuid) {
		LogUtils.info("将设备设为默认设备,openId:" + openId + ",tuid:" + tuid);
		if (StringUtils.isNull(openId, tuid)) {
			return new Result().paramError("参数不能为空");
		}
		if (!isTuidMatchOpenId(openId, tuid)) {
			return new Result().serverError("用户与设备不匹配");
		}
		Account account = new Account();
		account.setDefaultDevice(tuid);
		try {
			Result updateAccountByUuid = accountService.updateAccountByUuid(openId, account);
			if (updateAccountByUuid.getCode() != 200) {
				return updateAccountByUuid;
			}
			Result listDeviceByOpenId = listDeviceByOpenId(openId);
			if (listDeviceByOpenId.getCode() != 200) {
				return listDeviceByOpenId;
			}
			@SuppressWarnings("unchecked")
			List<Device> devices = (List<Device>) listDeviceByOpenId.getData();
			for (Device device : devices) {
				// 默认设备
				if (device.getTuid().equals(tuid)) {
					device.setIsDefault(IcarConst.DEVICE_DEFAULT_YES);
				} else {
					device.setIsDefault(IcarConst.DEVICE_DEFAULT_NO);
				}
				deviceService.updateDeviceByTuid(tuid, device);
			}
			return new Result().ok(null);

		} catch (Exception e) {
			LogUtils.error("设为默认设备时发生异常," + e.getMessage());
			return new Result().serverError("服务器异常");
		}
	}

	private boolean isTuidMatchOpenId(String openId, String tuid) {
		// 查询tuid与openId是否匹配，防止接口攻击
		DeviceExample example = new DeviceExample();
		example.createCriteria().andTuidEqualTo(tuid).andUuidEqualTo(openId);
		Result deviceResult = deviceService.listDeviceByExample(example, 0, 0);
		if (deviceResult.getCode() != 200) {
			LogUtils.error("设备tuid与openId不对应");
			return false;
		}
		return true;
	}

	@Override
	public Result deleteDeviceByTuidAndOpenId(String openId, String tuid) {
		LogUtils.info("删除设备,openId:" + openId + ",tuid:" + tuid);
		if (StringUtils.isNull(openId, tuid)) {
			return new Result().paramsNull("参数不能为空");
		}
		try {
			if (!isTuidMatchOpenId(openId, tuid)) {
				LogUtils.error("用户与设备不匹配");
				return new Result().serverError("用户与设备不匹配");
			}
			return deviceService.deleteDeviceByTuid(tuid);
		} catch (Exception e) {
			LogUtils.error("删除设备时发生异常,msg:" + e.getMessage());
			return new Result().serverError("服务器异常");
		}
	}

	@Override
	public Result getDefaultDeviceByOpenId(String openId) {
		LogUtils.info("根据openId查找默认设备");
		if (StringUtils.isNull(openId)) {
			return new Result().paramError("参数不能为空");
		}
		Result result = accountService.getAccountByUuid(openId);
		if (result.getCode() != 200) {
			LogUtils.error("查询默认设备时,未查找到用户");
			return result;
		}
		Account account = (Account) result.getData();
		String defaultTuid = account.getDefaultDevice();
		if (StringUtils.isNull(defaultTuid)) {
			LogUtils.error("账号" + openId + "还未绑定默认设备");
			return new Result().noRecord("您还没有绑定默认设备");
		}
		return deviceService.getDeviceByTuid(defaultTuid);
	}

	@Override
	public Result updateDeviceByTuid(String tuid, Device device) {
		if (StringUtils.isNull(tuid) || device == null) {
			return new Result().paramsNull("参数不能为空");
		}
		try {
			device.setTuid(tuid);
			return deviceService.updateDeviceByTuid(tuid, device);
		} catch (Exception e) {
			LogUtils.error("服务器发生异常," + e.getMessage());
			return new Result().serverError("服务器异常");
		}
	}
}

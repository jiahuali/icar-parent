package com.changan.icar.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Location;
import com.changan.icar.comm.po.LocationExample;
import com.changan.icar.comm.service.LocationService;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.server.dao.LocationMapper;

//@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class LocationServiceImpl implements LocationService {
	@Autowired
	private LocationMapper locationMapper;

	@Override
	public Result listLocationsByTravelUuid(String travelUuid, Integer pageSize, Integer pages) {
		LogUtils.info("查询行程起始点，travelUuid:" + travelUuid);
		if (StringUtils.isNull(travelUuid)) {
			return new Result().paramsNull("travelUuid不能为空");
		}
		try {
			LocationExample example = new LocationExample();
			example.createCriteria().andTraUuidEqualTo(travelUuid);
			// 倒序排序
			example.setOrderByClause("`time` ASC");
			List<Location> locationList = locationMapper.selectByExample(example);
			if (locationList == null || locationList.size() == 0) {
				return new Result().noRecord("未查询到result");
			}
			return new Result().ok(locationList);
		} catch (Exception e) {
			LogUtils.error("查询operation发生异常，msg:" + e.getMessage());
			return new Result().serverError("查询operation失败");
		}
	}

	@Override
	public Result addLocationByTravelUuid(String travelUuid, Location location) {
		LogUtils.info("请求添加操作,travelUuid:" + travelUuid);
		if (StringUtils.isNull(travelUuid)) {
			LogUtils.error("添加位置必须有travelUuid");
			return new Result().paramsNull("添加位置必须有msgId");
		}
		if (location == null) {
			LogUtils.error("添加位置必须携带指令相关内容");
			return new Result().paramsNull("位置内容为空");
		}
		// 多重保障
		location.setTraUuid(travelUuid);
		LogUtils.info("位置内容:" + location.toString());
		try {
			int rows = locationMapper.insertSelective(location);
			if (rows > 0) {
				LogUtils.info("插入location成功");
				return new Result().ok(location);
			} else {
				LogUtils.info("插入location失败");
			}
		} catch (Exception e) {
			LogUtils.info("插入location发生异常,信息:" + e.getMessage());
		}
		return new Result().serverError("添加location失败");
	}

	@Override
	public Result updateLocationbyUuid(String uuid, Location location) {
		LogUtils.info("请求更新location，uuid：" + uuid);
		if (StringUtils.isNull(uuid) || location == null) {
			LogUtils.error("更新时必须携带uuid以及要更新的参数");
			return new Result().paramsNull("更新时必须携带uuid以及要更新的参数");
		}
		location.setUuid(uuid);
		LogUtils.info("要更新的location:" + location.toString());
		try {
			int rows = locationMapper.updateByPrimaryKeySelective(location);
			if (rows == 1) {
				LogUtils.info("更新成功:" + location.toString());
				return new Result().ok(null);
			}
		} catch (Exception e) {
			LogUtils.error("更新location时发生异常,msg:" + e.getMessage());
		}
		LogUtils.error("更新失败：" + location.toString());
		return new Result().serverError("更新location时发生错误");
	}

}

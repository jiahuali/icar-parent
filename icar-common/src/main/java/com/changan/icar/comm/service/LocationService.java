package com.changan.icar.comm.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Location;

public interface LocationService {
	/**
	 * 查询行程的起始点
	 * 
	 * @return
	 */
	Result listLocationsByTravelUuid(String travelUuid, Integer pageSize, Integer pages);

	/**
	 * 添加行程的起始点
	 * 
	 * @return
	 */
	Result addLocationByTravelUuid(String travelUuid, Location location);

	// /**
	// * 删除行程的起始点
	// *
	// * @return
	// */
	// Result deleteLocationByTravelUuid(String travelUuid);

	/**
	 * 更新行程的起始点
	 */
	Result updateLocationbyUuid(String uuid, Location location);
}

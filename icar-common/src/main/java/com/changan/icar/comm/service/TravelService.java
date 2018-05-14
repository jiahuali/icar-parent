package com.changan.icar.comm.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Travel;
import com.changan.icar.comm.po.TravelExample;

public interface TravelService {

	Result addTravelByTuid(String tuid, Travel travel);

	Result updateTravelByUuid(String uuid, Travel travel);

	Result deleteTravelByUuid(String uuid);

	Result listTravelByTuid(String tuid, Integer pageSize, Integer pages);

	Result listTravelByExample(TravelExample example, Integer pageSize, Integer pages);

}

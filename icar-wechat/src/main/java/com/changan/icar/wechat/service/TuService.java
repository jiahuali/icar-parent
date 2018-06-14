package com.changan.icar.wechat.service;

import java.math.BigDecimal;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Device;

public interface TuService {

	Result updateTuByTuid(String tuid, BigDecimal oilAmount, BigDecimal speed, Float heading, BigDecimal altitude,
			BigDecimal longitude, BigDecimal latitude);

}

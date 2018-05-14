package com.changan.icar.server.service;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Travel;
import com.changan.icar.comm.service.TravelService;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class TravelServiceTest {
	@Autowired
	private TravelService travelService;

	private Travel travel = new Travel();

	private String uuid = "11111111111111111111111111111111";

	private String tuid = "11111111111111111111111111111111";

	private BigDecimal milles = new BigDecimal(3);

	@Before
	public void init() {
		travel.setTuid(tuid);
		travel.setUuid(uuid);
		travel.setMileage(milles);
	}

	/**
	 * 预期插入成功，code：200
	 */
	@Test
	public void insert1() {

		Result result = travelService.addTravelByTuid(tuid, travel);
		System.out.println("insert 1:" + result.toString());
	}

	/**
	 * 该案例符合，预期更新成功，code：200
	 */
	@Test
	public void update() {
		BigDecimal mileage = new BigDecimal(55);
		travel.setMileage(mileage);
		Result updateTravelByUuid = travelService.updateTravelByUuid(uuid, travel);
		System.out.println(updateTravelByUuid);
	}

	@Test
	public void list() {
		Result listTravelByTuid = travelService.listTravelByTuid(tuid, 0, 0);
		System.out.println(listTravelByTuid.toString());
	}
}

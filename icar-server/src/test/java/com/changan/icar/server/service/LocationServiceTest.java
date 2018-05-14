package com.changan.icar.server.service;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Location;
import com.changan.icar.comm.service.LocationService;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class LocationServiceTest {
	@Autowired
	private LocationService locationService;

	private Location location = new Location();

	private String traUuid = "11111111111111111111111111111111";

	private String uuid = "11111111111111111111111111111111";

	@Before
	public void init() {
		location.setUuid(uuid);
		location.setName("重庆交通大学");
		location.setLatitude(new BigDecimal(122.01));
		location.setLongitude(new BigDecimal(122.01));
		location.setTime(new Date());
		location.setTraUuid(traUuid);
	}

	/**
	 * 预期插入成功，code：200
	 */
	@Test
	public void insert1() {
		Result result = locationService.addLocationByTravelUuid(traUuid, location);
		System.out.println("insert 1:" + result.toString());
	}

	/**
	 * 该案例符合，预期更新成功，code：200
	 */
	@Test
	public void update() {
		location.setAltitude(new BigDecimal(1000));
		Result updateLocationbyUuid = locationService.updateLocationbyUuid(traUuid, location);
		System.out.println(updateLocationbyUuid.toString());
	}

	@Test
	public void list() {
		Result listIdea = locationService.listLocationsByTravelUuid(traUuid, 0, 0);
		System.out.println(listIdea.toString());
	}
}

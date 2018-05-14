package com.changan.icar.server.service;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Device;
import com.changan.icar.comm.service.DeviceService;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class DeviceServiceTest {
	@Autowired
	private DeviceService deviceService;

	private Device device = new Device();

	private String tuid = "11111111111111111111111111111111";

	private String model = "C301";

	private String name = "reactonCC";

	@Before
	public void init() {

	}

	/**
	 * 外键约束，预期插入失败，code：500
	 */
	@Test
	public void insert1() {
		String uuid = "sssss";
		Device device = new Device();
		device.setName(name);
		Result result = deviceService.addDeviceByAccountUuid(uuid, tuid, model, device);
		System.out.println("insert 2:" + result.toString());
	}

	/**
	 * 该案例符合，预期插入成功，code：200
	 */
	@Test
	public void insert2() {
		String uuid = "wxid_223222233345";
		Device device = new Device();
		device.setName(name);
		Result result = deviceService.addDeviceByAccountUuid(uuid, tuid, model, device);
		System.out.println("insert 1:" + result.toString());
	}

	/**
	 * 该案例符合，预期更新成功，code：200
	 */
	@Test
	public void update() {
		device.setCarNumber("渝D66666");
		deviceService.updateDeviceByTuid(tuid, device);
	}

	@Test
	public void getDevice() {
		Result deviceByTuid = deviceService.getDeviceByTuid(tuid);
		System.out.println(deviceByTuid.toString());
	}

	@Test
	public void listDevice() {
		Result listDevice = deviceService.listDevice(0, 0);
		System.out.println(listDevice.toString());
	}
	
	@Test
	public void deleteDevice() {
		Result listDevice = deviceService.deleteDeviceByTuid(tuid);
		System.out.println(listDevice.toString());
	}
}

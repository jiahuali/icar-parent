package com.changan.icar.server.service;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.changan.icar.comm.service.PushService;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class PushServiceTest {
	@Autowired
	private PushService pushService;

	private String alias = "11111111111111111111111111112211";

	@Before
	public void init() {

	}

	/**
	 * 预期插入成功，code：200
	 */
	@Test
	public void sendWithAlias() {
		String content = "我是推送alias";
		pushService.pushMessageWithAlias(alias, content);
	}

	/**
	 * 该案例符合，预期更新成功，code：200
	 */
	@Test
	public void sendAll() {
		String content = "我是推送全体";
		pushService.pushMessageAll(content);
	}

}

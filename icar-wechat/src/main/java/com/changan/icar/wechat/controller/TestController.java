package com.changan.icar.wechat.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;
import com.changan.icar.wechat.service.TestService;

@RestController
public class TestController {
	@Autowired
	private TestService testService;
	private static Logger log = Logger.getLogger(TestController.class);

	@GetMapping("/hello")
	public Result hello(String openId, String username, Account account) {
		log.info("请求hello，openID:" + openId + ",username:" + username);
		return testService.insert(openId, username, account);
	}

}

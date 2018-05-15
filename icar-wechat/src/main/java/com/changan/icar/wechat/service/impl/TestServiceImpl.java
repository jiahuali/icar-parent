package com.changan.icar.wechat.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;
import com.changan.icar.comm.service.AccountService;
import com.changan.icar.wechat.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Reference(version = "1.0.0")
	private AccountService accountService;

	private static Logger log = Logger.getLogger(TestService.class);

	@Override
	public Result insert(String openId, String username, Account account) {
		log.info("testService，from wechat");
		return accountService.createAccount(openId, username, account);
	}
}

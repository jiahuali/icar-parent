package com.changan.icar.wechat.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
	@Autowired
	private TestService testService;

	@org.junit.Test
	public void test() {
		String username = "ljh";
		String openId = "ljh2234";
		Account account = new Account();
	
		Result result = testService.insert(openId, username, account);
		System.out.println(result);
	}
}

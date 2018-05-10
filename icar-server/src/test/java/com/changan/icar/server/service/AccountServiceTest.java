package com.changan.icar.server.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
	@Autowired
	private AccountService accountService;
	@Test
	public void addAccountByWx(){
		String openId = "wxid_223222233345"; 
		String username = "caLee";
		Result result = accountService.createAccountFromWx(openId, username, null);
		System.out.println(result);
		
	}
}

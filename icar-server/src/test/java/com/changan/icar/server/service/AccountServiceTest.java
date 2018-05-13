package com.changan.icar.server.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.changan.icar.comm.dto.IcarConst;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;
import com.changan.icar.comm.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
	@Autowired
	private AccountService accountService;

	@Test
	public void addAccountByWx() {
		String uuid = "wxid_223222233345";
		String username = "caLee";
		Integer userFrom = IcarConst.ACCOUNT_FROM_WX;
		Account account = new Account();
		account.setUserFrom(userFrom);
		Result result = accountService.createAccount(uuid, username, account);
		System.out.println(result);
	}

	@Test
	public void addAccountByWx2() {
		String uuid = "";
		String username = "caLee";
		Integer userFrom = IcarConst.ACCOUNT_FROM_WX;
		Account account = new Account();
		account.setUserFrom(userFrom);
		Result result = accountService.createAccount(uuid, username, account);
		System.out.println(result);
	}

	@Test
	public void addAccountByWx3() {
		String uuid = "wxid_223222233345";
		String username = "";
		Integer userFrom = IcarConst.ACCOUNT_FROM_WX;
		Account account = new Account();
		account.setUserFrom(userFrom);
		Result result = accountService.createAccount(uuid, username, account);
		System.out.println(result);
	}

	@Test
	public void addAccountByWx4() {
		String uuid = "";
		String username = "";
		Integer userFrom = IcarConst.ACCOUNT_FROM_WX;
		Account account = new Account();
		account.setUserFrom(userFrom);
		Result result = accountService.createAccount(uuid, username, account);
		System.out.println(result);
	}

	@Test
	public void addAccountByWx5() {
		String uuid = "wxid_223222233345";
		String username = "ca";
		Integer userFrom = IcarConst.ACCOUNT_FROM_WX;
		Account account = new Account();
		account.setUserFrom(userFrom);
		Result result = accountService.createAccount(uuid, username, account);
		System.out.println(result);
	}

	@Test
	public void updateAccountByUuid() {
		String uuid = "wxid_223222233345";
		String username = "ca";
		Account account = new Account();
		account.setUsername(username);
		Result result = accountService.updateAccountByUuid(uuid, account);
		System.out.println(result);
	}

	@Test
	public void selectAccountByUuid() {
		String uuid = "wxid_223222233345";
		Result result = accountService.getAccountByUuid(uuid);
		System.out.println(result);
	}

	@Test
	public void listAccount() {
		Result result = accountService.listAccount(0, 0);
		System.out.println(result);
	}

}

package com.changan.icar.server.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.changan.icar.comm.po.Account;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest {
	@Autowired
	private AccountMapper accountMapper;

	@Test
	public void add() {
		Account record = new Account();
		record.setUuid("thisIsMe");
		record.setUsername("我是李嘉华");
		accountMapper.insertSelective(record);
	}
}

package com.changan.icar.wechat.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;

public interface AccountService {
	Result addAccountByOpenId(String openId, String username, Account account);

	Result getAccountByOpenId(String openId);
}

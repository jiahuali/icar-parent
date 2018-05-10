package com.changan.icar.wechat.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;

public interface TestService {

	Result insert(String openId, String username, Account account);
}

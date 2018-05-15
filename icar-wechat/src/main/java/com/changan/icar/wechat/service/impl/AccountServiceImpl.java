package com.changan.icar.wechat.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.wechat.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Reference(version = "1.0.0")
	private com.changan.icar.comm.service.AccountService accountService;

	@Override
	public Result addAccountByOpenId(String openId, String username, Account account) {
		LogUtils.info("请求创建用户,uuid:" + openId + ",username:" + username);
		if (StringUtils.isNull(openId, username)) {
			return new Result().paramsNull("参数不能为空");
		}
		return accountService.createAccount(openId, username, account);
	}

	@Override
	public Result getAccountByOpenId(String openId) {
		LogUtils.info("请求查询用户,openId:" + openId);
		if (StringUtils.isNull(openId)) {
			return new Result().paramsNull("参数不能为空");
		}
		return accountService.getAccountByUuid(openId);
	}

}

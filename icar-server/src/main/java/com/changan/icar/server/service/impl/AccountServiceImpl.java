package com.changan.icar.server.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.changan.icar.comm.dto.IcarConst;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;
import com.changan.icar.comm.service.AccountService;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.server.dao.AccountMapper;

@Service(version = "1.0.0")
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountMapper accountMapper;

	private static Logger log = Logger.getLogger(AccountServiceImpl.class);

	@Override
	public Result updateAccountByUuid(String uuid, Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getAccountByUuid(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result listAccount(int pageSize, int pages) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result createAccountFromWx(String openId, String username, Account account) {
		log.info("请求来自微信端，添加用户,openId:" + openId 
				+ ",username:" + username);
		
		if (StringUtils.isNull(username, openId)) {
			log.error("微信注册必须有用户名和openId");
			return new Result().paramsNull("微信注册必须有用户名和openId");
		}
		if(account==null){
			account = new Account();
		}
		// 多重保障
		account.setUsername(username);
		account.setUuid(openId);
		account.setUserFrom(IcarConst.ACCOUNT_FROM_CLIENT);

		return addAccount(account);
	}

	@Override
	public Result createAccountFromClient(String username, String phone, Account account) {
		log.info("请求来自客户端，添加用户,username:" + username 
				+ ",phone:" + phone);
		if (StringUtils.isNull(username, phone)) {
			log.error("注册必须填写用户名和电话");
			return new Result().paramsNull("注册必须填写用户名和电话");
		}
		if (account == null) {
			account = new Account();
		}
		// 多重保障
		account.setUsername(username);
		account.setPhone(phone);
		account.setUserFrom(IcarConst.ACCOUNT_FROM_CLIENT);

		return addAccount(account);
	}

	private Result addAccount(Account account) {
		log.info("插入account,"+account.toString());
		try {
			int rows = accountMapper.insertSelective(account);
			if (rows > 0) {
				log.info("插入account成功");
				return new Result().ok(account);
			}else{
				log.info("插入account失败");
			}
		} catch (Exception e) {
			log.info("插入account发生异常,信息:"+e.getMessage());
		}
		return new Result().serverError("添加用户失败，原因未知，错误发生在插入数据库时");
	}

}

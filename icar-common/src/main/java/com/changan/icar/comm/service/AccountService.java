package com.changan.icar.comm.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;
import com.changan.icar.comm.po.AccountExample;
import com.changan.icar.comm.po.AccountExample.Criteria;

public interface AccountService {
	/**
	 * 创建用户，来自微信的用户直接使用openId当做uuid
	 * 
	 * @param account
	 * @return
	 */
	Result createAccount(String uuid, String username, Account account);

	/**
	 * 根据uuid更新用户
	 * 
	 * @param uuid
	 * @param account
	 * @return
	 */
	Result updateAccountByUuid(String uuid, Account account);

	/**
	 * 根据uuid获取用户
	 * 
	 * @param uuid
	 * @return
	 */
	Result getAccountByUuid(String uuid);

	/**
	 * 条件查询
	 * 
	 * @param accountExample
	 * @param pageSize
	 * @param pages
	 * @return
	 */
	Result listAccountsByCriteria(AccountExample accountExample, Integer pageSize, Integer pages);

	/**
	 * 分页查询
	 * 
	 * @param pageSize
	 * @param pages
	 * @return
	 */
	Result listAccount(Integer pageSize, Integer pages);

}

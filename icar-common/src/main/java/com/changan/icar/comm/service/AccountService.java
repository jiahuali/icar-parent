package com.changan.icar.comm.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;

public interface AccountService {
	/**
	 * 创建用户，来自微信的用户直接使用openId当做uuid
	 * 
	 * @param account
	 * @return
	 */
	Result createAccountFromWx(String openId, String username, Account account);

	/**
	 * 来自客户端的用户
	 * 
	 * @param account
	 * @return
	 */
	Result createAccountFromClient(String username, String phone, Account account);

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
	 * 分页查询
	 * 
	 * @param pageSize
	 * @param pages
	 * @return
	 */
	Result listAccount(int pageSize, int pages);

}

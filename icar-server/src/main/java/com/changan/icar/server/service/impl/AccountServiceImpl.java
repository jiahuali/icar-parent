package com.changan.icar.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.changan.icar.comm.dto.IcarConst;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;
import com.changan.icar.comm.po.AccountExample;
import com.changan.icar.comm.service.AccountService;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.server.dao.AccountMapper;

//@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public Result createAccount(String uuid, String username, Account account) {
		LogUtils.info("请求添加用户,uuid:" + uuid + ",username:" + username);
		if (StringUtils.isNull(username, uuid)) {
			LogUtils.error("注册必须有uuid和username");
			return new Result().paramsNull("注册必须填写用户名和电话");
		}
		if (account == null) {
			LogUtils.error("未提供构造的account");
			return new Result().paramsNull("未提供构造的account");
		}
		// 多重保障
		account.setUsername(username);
		account.setUuid(uuid);
		account.setStatus(IcarConst.ACCOUNT_STATUS_OK);
		LogUtils.info("插入account," + account.toString());
		try {
			int rows = accountMapper.insertSelective(account);
			if (rows > 0) {
				LogUtils.info("插入account成功");
				return new Result().ok(account);
			} else {
				LogUtils.info("插入account失败");
			}
		} catch (Exception e) {
			LogUtils.info("插入account发生异常,信息:" + e.getMessage());
		}
		return new Result().serverError("添加用户失败，原因未知，错误发生在插入数据库时");
	}

	@Override
	public Result updateAccountByUuid(String uuid, Account account) {
		LogUtils.info("请求更新account，uuid：" + uuid);
		if (StringUtils.isNull(uuid) || account == null) {
			LogUtils.error("更新时必须携带uuid以及要更新的参数");
			return new Result().paramsNull("更新时必须携带uuid以及要更新的参数");
		}
		account.setUuid(uuid);
		LogUtils.info("要更新的account:" + account.toString());
		try {
			int rows = accountMapper.updateByPrimaryKeySelective(account);
			if (rows == 1) {
				LogUtils.info("更新成功:" + account.toString());
				return new Result().ok(null);
			}
		} catch (Exception e) {
			LogUtils.error("更新account时发生异常,msg:" + e.getMessage());
		}
		LogUtils.error("更新失败：" + account.toString());
		return new Result().serverError("更新用户时发生错误");
	}

	@Override
	public Result getAccountByUuid(String uuid) {
		LogUtils.info("正在根据uuid查询用户，uuid:" + uuid);
		if (StringUtils.isNull(uuid)) {
			LogUtils.error("根据uuid查询用户时，uuid不能为空");
			return new Result().paramsNull("根据uuid查询用户时，uuid不能为空");
		}
		try {
			Account account = accountMapper.selectByPrimaryKey(uuid);
			if (account == null) {
				LogUtils.info("未查询到account,uuid:" + uuid);
				return new Result().noRecord("未查询到account");
			}
			LogUtils.info("查询到的account:" + account.toString());
			return new Result().ok(account);
		} catch (Exception e) {
			LogUtils.error("查询account发生异常，msg:" + e.getMessage());
			return new Result().serverError("查询account失败");
		}

	}

	@Override
	public Result listAccountsByExample(AccountExample example, Integer pageSize, Integer pages) {
		if (example == null) {
			// 查询条件为空，不予返回
			example = new AccountExample();
		}
		LogUtils.info("查询accountList，" + example);
		try {
			List<Account> accountList = accountMapper.selectByExample(example);
			if (accountList == null || accountList.size() == 0) {
				return new Result().noRecord("未查询到result");
			}
			return new Result().ok(accountList);
		} catch (Exception e) {
			LogUtils.error("查询account列表发生错误，" + e.getMessage());
			return new Result().serverError("查询account列表失败");
		}
	}

	@Override
	public Result listAccount(Integer pageSize, Integer pages) {
		return listAccountsByExample(null, pageSize, pages);
	}
}

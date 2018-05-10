package com.changan.icar.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.changan.icar.comm.po.Account;
import com.changan.icar.comm.po.AccountExample;

public interface AccountMapper {
	long countByExample(AccountExample example);

	int deleteByExample(AccountExample example);

	int deleteByPrimaryKey(String uuid);

	int insert(Account record);

	int insertSelective(Account record);

	List<Account> selectByExampleWithBLOBs(AccountExample example);

	List<Account> selectByExample(AccountExample example);

	Account selectByPrimaryKey(String uuid);

	int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

	int updateByExampleWithBLOBs(@Param("record") Account record, @Param("example") AccountExample example);

	int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

	int updateByPrimaryKeySelective(Account record);

	int updateByPrimaryKeyWithBLOBs(Account record);

	int updateByPrimaryKey(Account record);
}
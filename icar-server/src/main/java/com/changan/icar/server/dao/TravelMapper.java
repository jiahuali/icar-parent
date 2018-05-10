package com.changan.icar.server.dao;

import com.changan.icar.comm.po.Travel;
import com.changan.icar.comm.po.TravelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TravelMapper {
	long countByExample(TravelExample example);

	int deleteByExample(TravelExample example);

	int deleteByPrimaryKey(String uuid);

	int insert(Travel record);

	int insertSelective(Travel record);

	List<Travel> selectByExample(TravelExample example);

	Travel selectByPrimaryKey(String uuid);

	int updateByExampleSelective(@Param("record") Travel record, @Param("example") TravelExample example);

	int updateByExample(@Param("record") Travel record, @Param("example") TravelExample example);

	int updateByPrimaryKeySelective(Travel record);

	int updateByPrimaryKey(Travel record);
}
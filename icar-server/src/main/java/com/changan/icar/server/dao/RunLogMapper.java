package com.changan.icar.server.dao;

import com.changan.icar.comm.po.RunLog;
import com.changan.icar.comm.po.RunLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunLogMapper {
    long countByExample(RunLogExample example);

    int deleteByExample(RunLogExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(RunLog record);

    int insertSelective(RunLog record);

    List<RunLog> selectByExampleWithBLOBs(RunLogExample example);

    List<RunLog> selectByExample(RunLogExample example);

    RunLog selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") RunLog record, @Param("example") RunLogExample example);

    int updateByExampleWithBLOBs(@Param("record") RunLog record, @Param("example") RunLogExample example);

    int updateByExample(@Param("record") RunLog record, @Param("example") RunLogExample example);

    int updateByPrimaryKeySelective(RunLog record);

    int updateByPrimaryKeyWithBLOBs(RunLog record);

    int updateByPrimaryKey(RunLog record);
}
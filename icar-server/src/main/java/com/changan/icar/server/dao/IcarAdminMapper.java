package com.changan.icar.server.dao;

import com.changan.icar.comm.po.IcarAdmin;
import com.changan.icar.comm.po.IcarAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IcarAdminMapper {
    long countByExample(IcarAdminExample example);

    int deleteByExample(IcarAdminExample example);

    int deleteByPrimaryKey(String username);

    int insert(IcarAdmin record);

    int insertSelective(IcarAdmin record);

    List<IcarAdmin> selectByExampleWithBLOBs(IcarAdminExample example);

    List<IcarAdmin> selectByExample(IcarAdminExample example);

    IcarAdmin selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") IcarAdmin record, @Param("example") IcarAdminExample example);

    int updateByExampleWithBLOBs(@Param("record") IcarAdmin record, @Param("example") IcarAdminExample example);

    int updateByExample(@Param("record") IcarAdmin record, @Param("example") IcarAdminExample example);

    int updateByPrimaryKeySelective(IcarAdmin record);

    int updateByPrimaryKeyWithBLOBs(IcarAdmin record);

    int updateByPrimaryKey(IcarAdmin record);
}
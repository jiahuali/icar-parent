package com.changan.icar.server.dao;

import com.changan.icar.comm.po.Idea;
import com.changan.icar.comm.po.IdeaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IdeaMapper {
    long countByExample(IdeaExample example);

    int deleteByExample(IdeaExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(Idea record);

    int insertSelective(Idea record);

    List<Idea> selectByExampleWithBLOBs(IdeaExample example);

    List<Idea> selectByExample(IdeaExample example);

    Idea selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") Idea record, @Param("example") IdeaExample example);

    int updateByExampleWithBLOBs(@Param("record") Idea record, @Param("example") IdeaExample example);

    int updateByExample(@Param("record") Idea record, @Param("example") IdeaExample example);

    int updateByPrimaryKeySelective(Idea record);

    int updateByPrimaryKeyWithBLOBs(Idea record);

    int updateByPrimaryKey(Idea record);
}
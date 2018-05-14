package com.changan.icar.comm.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Idea;
import com.changan.icar.comm.po.IdeaExample;

public interface IdeaService {

	Result getIdeaByUuid(String uuid);

	Result addIdeaByAccountUuid(String accountUuid, Idea idea);

	Result updateIdeaByUuid(String uuid, Idea idea);

	Result listIdea(Integer pageSize, Integer pages);

	Result listIdeaByExample(IdeaExample example, Integer pageSize, Integer pages);

}

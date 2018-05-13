package com.changan.icar.comm.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Idea;
import com.changan.icar.comm.po.IdeaExample;

public interface IdeaService {

	Result addIdeaByAccountUuid(String accountUuid, Idea idea);

	Result updateIdeaByUuid(String msgId, Idea idea);

	Result listIdea(Integer pageSize, Integer pages);

	Result listIdeaByCriteria(IdeaExample example, Integer pageSize, Integer pages);

}

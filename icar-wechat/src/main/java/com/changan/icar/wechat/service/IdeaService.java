package com.changan.icar.wechat.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Idea;

public interface IdeaService {
	
	Result listIdeaByOpenId(String openId);
	
	Result addIdeaByOpenId(String openId,Idea idea);
}

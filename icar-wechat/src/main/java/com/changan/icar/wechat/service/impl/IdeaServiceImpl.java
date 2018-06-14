package com.changan.icar.wechat.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Idea;
import com.changan.icar.comm.po.IdeaExample;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.wechat.service.IdeaService;

@Service
public class IdeaServiceImpl implements IdeaService {

	@Reference(version = "1.0.0")
	private com.changan.icar.comm.service.IdeaService ideaService;

	@Override
	public Result listIdeaByOpenId(String openId) {
		if (StringUtils.isNull("openId")) {
			return new Result().paramsNull("参数不能为空");
		}
		try {
			IdeaExample example = new IdeaExample();
			example.setOrderByClause("`time` ASC");
			example.createCriteria().andAccUuidEqualTo(openId);
			Result result = ideaService.listIdeaByExample(example, 0, 0);
			if (result.getCode() != 200) {
				LogUtils.error("查询idea失败," + result.getMsg());
			}
			return result;
		} catch (Exception e) {
			LogUtils.error("查询idea list时发生异常，" + e.getMessage());
			return new Result().serverError("服务器发生异常");
		}

	}

	@Override
	public Result addIdeaByOpenId(String openId, Idea idea) {
		if (StringUtils.isNull(openId) || idea == null) {
			return new Result().paramsNull("参数不能为空");
		}
		if (StringUtils.isNull(idea.getContent(), idea.getTitle())) {
			return new Result().paramError("内容与标题不能为空");
		}
		idea.setAccUuid(openId);
		try {
			return ideaService.addIdeaByAccountUuid(openId, idea);
		} catch (Exception e) {
			LogUtils.error("添加idea时发生异常，" + e.getMessage());
			return new Result().serverError("服务器异常");
		}

	}

}

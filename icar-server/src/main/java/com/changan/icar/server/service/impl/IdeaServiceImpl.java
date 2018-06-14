package com.changan.icar.server.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Idea;
import com.changan.icar.comm.po.IdeaExample;
import com.changan.icar.comm.service.IdeaService;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.server.dao.IdeaMapper;

@Service(version = "1.0.0")
public class IdeaServiceImpl implements IdeaService {
	@Autowired
	private IdeaMapper ideaMapper;

	@Override
	public Result addIdeaByAccountUuid(String accountUuid, Idea idea) {
		LogUtils.info("请求添加创意操作,accountUuid:" + accountUuid);
		if (StringUtils.isNull(accountUuid)) {
			LogUtils.error("添加创意必须有accountUuid");
			return new Result().paramsNull("添加指令必须有msgId");
		}
		if (idea == null) {
			LogUtils.error("添加创意必须携带创意相关内容");
			return new Result().paramsNull("创意内容为空");
		}
		// 多重保障
		idea.setUuid(StringUtils.generateUuid());
		idea.setAccUuid(accountUuid);
		idea.setTime(new Date());
		LogUtils.info("idea内容:" + idea.toString());
		try {
			int rows = ideaMapper.insertSelective(idea);
			if (rows > 0) {
				LogUtils.info("插入idea成功");
				return new Result().ok(idea);
			} else {
				LogUtils.info("插入idea失败");
			}
		} catch (Exception e) {
			LogUtils.info("插入idea发生异常,信息:" + e.getMessage());
		}
		return new Result().serverError("添加idea失败");
	}

	@Override
	public Result updateIdeaByUuid(String uuid, Idea idea) {
		LogUtils.info("请求更新idea，：" + uuid);
		if (StringUtils.isNull(uuid) || idea == null) {
			LogUtils.error("更新时必须携带idea uuid以及要更新的参数");
			return new Result().paramsNull("更新时必须携带idea uuid以及要更新的参数");
		}
		idea.setUuid(uuid);
		LogUtils.info("要更新的idea:" + idea.toString());
		try {
			int rows = ideaMapper.updateByPrimaryKeySelective(idea);
			if (rows == 1) {
				LogUtils.info("更新成功:" + idea.toString());
				return new Result().ok(null);
			}
		} catch (Exception e) {
			LogUtils.error("更新idea时发生异常,msg:" + e.getMessage());
		}
		LogUtils.error("更新失败：" + idea.toString());
		return new Result().serverError("更新idea时发生错误");
	}

	@Override
	public Result listIdea(Integer pageSize, Integer pages) {
		return listIdeaByExample(null, pageSize, pages);
	}

	@Override
	public Result listIdeaByExample(IdeaExample example, Integer pageSize, Integer pages) {
		if (example == null) {
			example = new IdeaExample();
		}
		LogUtils.info("查询ideaList，" + example);
		try {
			List<Idea> operationList = ideaMapper.selectByExampleWithBLOBs(example);
			if (operationList == null || operationList.size() == 0) {
				return new Result().noRecord("未查询到result");
			}
			return new Result().ok(operationList);
		} catch (Exception e) {
			LogUtils.error("listIdeaByExample发生异常，msg:" + e.getMessage());
			return new Result().serverError("获取idea列表异常");
		}
	}

	@Override
	public Result getIdeaByUuid(String uuid) {
		LogUtils.info("查询idea，uuid:" + uuid);
		if (StringUtils.isNull(uuid)) {
			return new Result().paramsNull("uuid不能为空");
		}
		try {
			Idea idea = ideaMapper.selectByPrimaryKey(uuid);
			if (idea != null) {
				LogUtils.info("查询到的uuid:" + idea.toString());
				return new Result().ok(idea);
			}
			return new Result().noRecord("未查询到idea");
		} catch (Exception e) {
			LogUtils.error("查询idea发生异常，msg:" + e.getMessage());
			return new Result().serverError("查询idea失败");
		}
	}

}

package com.changan.icar.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Feedback;
import com.changan.icar.comm.po.FeedbackExample;
import com.changan.icar.comm.service.FeedbackService;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.server.dao.FeedbackMapper;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackMapper feedbackMapper;

	@Override
	public Result listFeedbackByIdeaUuid(String ideaUuid) {
		if (StringUtils.isNull(ideaUuid)) {
			return new Result().paramsNull("ideaUuid为空");
		}
		LogUtils.info("查询评论列表，" + ideaUuid);
		try {
			FeedbackExample example = new FeedbackExample();
			// 倒序排序
			example.setOrderByClause("`time` ASC");
			example.createCriteria().andIdeUuidEqualTo(ideaUuid);
			;
			List<Feedback> feedbackList = feedbackMapper.selectByExample(example);
			if (feedbackList == null || feedbackList.size() == 0) {
				return new Result().noRecord("未查询到feedback");
			}
			return new Result().ok(feedbackList);
		} catch (Exception e) {
			LogUtils.error("listFeedbackByIdeaUuid发生异常，msg:" + e.getMessage());
			return new Result().serverError("获取feedback列表异常");
		}
	}

	@Override
	public Result addFeedbackByIdeaUuid(String ideaUuid, Feedback feedback) {
		LogUtils.info("请求添加评论操作,ideaUuid:" + ideaUuid);
		if (StringUtils.isNull(ideaUuid)) {
			LogUtils.error("添加评论必须有ideaUuid");
			return new Result().paramsNull("添加评论必须有ideaUuid");
		}
		if (feedback == null) {
			LogUtils.error("添加评论必须携带评论相关内容");
			return new Result().paramsNull("评论内容为空");
		}
		// 多重保障
		feedback.setIdeUuid(ideaUuid);
		LogUtils.info("feedback内容:" + feedback.toString());
		try {
			int rows = feedbackMapper.insertSelective(feedback);
			if (rows > 0) {
				LogUtils.info("插入feedback成功");
				return new Result().ok(feedback);
			} else {
				LogUtils.info("插入feedback失败");
			}
		} catch (Exception e) {
			LogUtils.info("插入feedback发生异常,信息:" + e.getMessage());
		}
		return new Result().serverError("添加feedback失败");
	}

}

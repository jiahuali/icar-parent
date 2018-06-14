package com.changan.icar.wechat.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.changan.icar.comm.dto.IcarConst;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Feedback;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.wechat.service.FeedbackService;
@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Reference(version = "1.0.0")
	private com.changan.icar.comm.service.FeedbackService feedbackService;

	@Override
	public Result listFeedbackByIdeaUuid(String ideaUuid) {
		if (StringUtils.isNull(ideaUuid)) {
			return new Result().paramsNull("参数不能为空");
		}
		try {
			return feedbackService.listFeedbackByIdeaUuid(ideaUuid);
		} catch (Exception e) {
			LogUtils.error("获取feedback时发生异常," + e.getMessage());
			return new Result().serverError("服务器发生异常");
		}

	}

	@Override
	public Result addFeedbackByIdeaUuid(String ideaUuid, Feedback feedback) {
		if (StringUtils.isNull(ideaUuid) || feedback == null) {
			return new Result().paramsNull("参数不能为空");
		}

		if (StringUtils.isNull(feedback.getContent())) {
			return new Result().paramError("content不能为空");
		}

		feedback.setIdeUuid(ideaUuid);
		feedback.setType(IcarConst.FEEDBACK_FROM_USER);
		try {
			return feedbackService.addFeedbackByIdeaUuid(ideaUuid, feedback);
		} catch (Exception e) {
			LogUtils.error("添加feedback时发生异常," + e.getMessage());
			return new Result().serverError("服务器异常");
		}

	}

}

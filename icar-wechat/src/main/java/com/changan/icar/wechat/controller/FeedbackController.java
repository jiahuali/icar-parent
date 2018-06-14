package com.changan.icar.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Feedback;
import com.changan.icar.wechat.service.FeedbackService;

@RestController
@RequestMapping("feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@GetMapping("/listFeedbacksByIdeaUuid")
	public Result listFeedbacksByIdeaUuid(String ideaUuid) {
		return feedbackService.listFeedbackByIdeaUuid(ideaUuid);
	}

	@PostMapping("/addFeedbackByIdeaUuid")
	public Result addFeedbackByIdeaUuid(String ideaUuid, Feedback feedback) {
		return feedbackService.addFeedbackByIdeaUuid(ideaUuid, feedback);
	}

}

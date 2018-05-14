package com.changan.icar.server.service;

import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.changan.icar.comm.dto.IcarConst;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Feedback;
import com.changan.icar.comm.service.FeedbackService;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class FeedbackServiceTest {
	@Autowired
	private FeedbackService feedbackService;

	private Feedback feedback = new Feedback();

	private String uuid = "11111111111111111111111111111111";

	@Before
	public void init() {
		feedback.setContent("我也不知道");
		feedback.setIdeUuid(uuid);
		feedback.setTime(new Date());
		feedback.setUuid(uuid);
		feedback.setType(IcarConst.FEEDBACK_FROM_USER);
	}

	/**
	 * 预期插入成功，code：200
	 */
	@Test
	public void insert1() {
		Result result = feedbackService.addFeedbackByIdeaUuid(uuid, feedback);
		System.out.println("insert 1:" + result.toString());
	}

	@Test
	public void list() {
		Result listIdea = feedbackService.listFeedbackByIdeaUuid(uuid);
		System.out.println(listIdea.toString());
	}
}

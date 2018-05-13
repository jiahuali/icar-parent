package com.changan.icar.comm.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Feedback;

public interface FeedbackService {

	Result listFeedbackByIdeaUuid(String ideaUuid);

	Result addFeedbackByIdeaUuid(String ideaUuid, Feedback feedback);

}

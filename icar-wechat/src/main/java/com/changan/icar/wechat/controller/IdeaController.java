package com.changan.icar.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Idea;
import com.changan.icar.wechat.service.IdeaService;

@RestController
@RequestMapping("idea")
public class IdeaController {

	@Autowired
	private IdeaService ideaService;

	@GetMapping("/listIdeaByOpenId")
	public Result listIdeaByOpenId(String openId) {
		return ideaService.listIdeaByOpenId(openId);
	}

	@PostMapping("/addIdeaByOpenId")
	public Result addIdeaByOpenId(String openId, Idea idea) {
		return ideaService.addIdeaByOpenId(openId, idea);
	}

}

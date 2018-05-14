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
import com.changan.icar.comm.po.Idea;
import com.changan.icar.comm.service.IdeaService;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class IdeaServiceTest {
	@Autowired
	private IdeaService ideaService;

	private Idea idea = new Idea();

	private String accUuid = "wxid_223222233345";

	private String uuid = "11111111111111111111111111111111";

	@Before
	public void init() {
		idea.setAccUuid(accUuid);
		idea.setUuid(uuid);
		idea.setStatus(IcarConst.IDEA_STATUS_UNCHECKED);
		idea.setTime(new Date());
		idea.setTitle("我要举报");
		idea.setType(IcarConst.IDEA_TYPE_DEFAULT);
		idea.setContent("我为什么这么帅");
	}

	/**
	 * 预期插入成功，code：200
	 */
	@Test
	public void insert1() {
		Result result = ideaService.addIdeaByAccountUuid(accUuid, idea);
		System.out.println("insert 1:" + result.toString());
	}

	/**
	 * 该案例符合，预期更新成功，code：200
	 */
	@Test
	public void update() {
		idea.setContent("你真的很不错");
		Result updateIdeaByUuid = ideaService.updateIdeaByUuid(uuid, idea);
		System.out.println(updateIdeaByUuid.toString());
	}

	@Test
	public void get() {
		Result ideaRes = ideaService.getIdeaByUuid(uuid);
		System.out.println(ideaRes.toString());
	}

	@Test
	public void list() {
		Result listIdea = ideaService.listIdea(0, 0);
		System.out.println(listIdea.toString());
	}
}

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
import com.changan.icar.comm.po.Operation;
import com.changan.icar.comm.service.OperationService;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class OperationServiceTest {
	@Autowired
	private OperationService operationService;

	private Operation operation = new Operation();

	private String msgId = "11111111111111111111111111111111";

	private String tuid = "11111111111111111111111111111111";

	private String uuid = "wxid_223222233345";

	@Before
	public void init() {
		operation.setStatus(IcarConst.OPERATION_STATUS_WAIT);
		operation.setType(IcarConst.OPERATION_TYPE_REQUEST);
	}

	/**
	 * 预期插入成功，code：200
	 */
	@Test
	public void insert1() {
		operation.setOperation(IcarConst.OPERATION_OPER_NAVI);
		operation.setDatetime(new Date());
		operation.setTuid(tuid);
		operation.setUuid(uuid);

		Result result = operationService.addOperation(msgId, operation);
		System.out.println("insert 2:" + result.toString());
	}

	/**
	 * 该案例符合，预期更新成功，code：200
	 */
	@Test
	public void update() {
		operation.setExt("hello world");
		operationService.updateOperationByMsgId(msgId, operation);
	}

	@Test
	public void getOperation() {
		Result operationRes = operationService.getOperationByMsgId(msgId);
		System.out.println(operationRes.toString());
	}

	@Test
	public void listOperation() {
		Result listOpeartion = operationService.listOperationByTuid(tuid, 0, 0);
		System.out.println(listOpeartion.toString());
	}
}

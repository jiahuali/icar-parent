package com.changan.icar.wechat.service;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.changan.icar.comm.dto.IcarConst;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Operation;

@FixMethodOrder(MethodSorters.DEFAULT)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationServiceTest {
	@Autowired
	private OperationService operationService;

	private String accountUuid = "wxid_223222233345";

	private Operation operation;

	@org.junit.Before
	public void init() {
		operation = new Operation();
		operation.setTuid("21001001170715110000000643012698");
		operation.setExt("sss");
		operation.setMsgId("11111");
		operation.setOperation(IcarConst.OPERATION_OPER_NAVI);
		operation.setType(IcarConst.OPERATION_TYPE_RESPONSE);
	}

	@org.junit.Test
	public void exec() {
		Result execute = operationService.execute(accountUuid, operation);
		System.out.println(execute.toString());
	}
}

package com.changan.icar.wechat.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.changan.icar.comm.dto.IcarConst;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;
import com.changan.icar.comm.po.Operation;
import com.changan.icar.wechat.service.OperationService;
import com.changan.icar.wechat.service.TestService;

@RestController
public class TestController {
	@Autowired
	private TestService testService;
	@Autowired
	private OperationService operationService;
	private static Logger log = Logger.getLogger(TestController.class);

	@GetMapping("/hello")
	public Result hello(String openId, String username, Account account) {
		log.info("请求hello，openID:" + openId + ",username:" + username);
		return testService.insert(openId, username, account);
	}

	@GetMapping("/exec")
	public Result exec(String accountUuid) {
		log.info("请求exec,accountUuid:" + accountUuid);
		Operation operation = new Operation();
		operation.setExt("sss");
		operation.setOperation(IcarConst.OPERATION_OPER_NAVI);
		operation.setType(IcarConst.OPERATION_TYPE_RESPONSE);
		return operationService.execute(accountUuid, operation);
	}

	@GetMapping("/update")
	public Result update(String msgId, Operation operation) {
		return operationService.updateOperationByMsgId(msgId, operation);
	}

}

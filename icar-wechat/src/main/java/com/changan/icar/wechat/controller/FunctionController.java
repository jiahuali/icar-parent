package com.changan.icar.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.changan.icar.comm.dto.IcarConst;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Operation;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.wechat.service.OperationService;

@RestController
@RequestMapping("function")
public class FunctionController {
	@Autowired
	private OperationService operationService;

	@GetMapping("/generatePickCodeByOpenId")
	public Result generatePickCodeByOpenId(String openId, Integer operation) {
		Operation operationObj = new Operation();
		// 设置type为响应式请求
		operationObj.setType(IcarConst.OPERATION_TYPE_REQUEST);
		operationObj.setOperation(operation);
		// 根据openId创建接人指令
		Result result = operationService.addOperationByUuid(openId, operationObj);
		if (result.getCode() != 200) {
			LogUtils.error("创建指令失败，" + result.getCode() + ":" + result.getMsg());
			return result;
		}
		operationObj = (Operation) result.getData();
		return new Result().ok(operationObj.getMsgId());
	}

	@PostMapping("/updateMsgByMsgId")
	public Result updateMsgByMsgId(String msgId, Operation operation) {
		return operationService.updateOperationByMsgId(msgId, operation);
	}

	@GetMapping("/getOperationStatusByMsgId")
	public Result getOperationStatusByMsgId(String msgId) {
		try {
			Result result = operationService.getOperationByMsgId(msgId);
			if (result.getCode() == 200) {
				// 只返回status
				Operation operation = (Operation) result.getData();
				return new Result().ok(operation.getStatus());
			}
			return result;
		} catch (Exception e) {
			LogUtils.error("查询指令状态时发生异常，" + e.getMessage());
			return new Result().serverError("服务器异常");
		}

	}

	@GetMapping("/getOperationByMsgId")
	public Result getOperationByMsgId(String msgId) {
		return operationService.getOperationByMsgId(msgId);
	}

	@GetMapping("/push2DeviceByTuid")
	public Result push2DeviceByTuid(String tuid, String msgId, String openId) {
		return operationService.push2DeviceByTuid(msgId, tuid, openId);
	}

}

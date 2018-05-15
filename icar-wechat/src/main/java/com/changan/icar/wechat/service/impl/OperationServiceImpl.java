package com.changan.icar.wechat.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.changan.icar.comm.dto.IcarConst;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Account;
import com.changan.icar.comm.po.Operation;
import com.changan.icar.comm.service.AccountService;
import com.changan.icar.comm.service.PushService;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.wechat.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {
	@Reference(version = "1.0.0")
	private com.changan.icar.comm.service.OperationService operationService;
	@Reference(version = "1.0.0")
	private PushService pushService;
	@Reference(version = "1.0.0")
	private AccountService accountService;

	@Override
	public Result execute(String accountUuid, Operation operation) {
		if (operation == null || StringUtils.isNull(operation.getMsgId(), accountUuid)) {
			return new Result().paramError("执行指令内容为空");
		}
		try {
			Result defaultDeviceTuidRes = getDefaultDeviceTuidByUuid(accountUuid);
			if (defaultDeviceTuidRes.getCode() != 200) {
				LogUtils.error("未能找到默认设备，msg:" + defaultDeviceTuidRes.getMsg());
				return defaultDeviceTuidRes;
			}
			String defaultTuid = (String) defaultDeviceTuidRes.getData();
			// 设置执行的车辆
			operation.setTuid(defaultTuid);
			// 等待状态
			operation.setStatus(IcarConst.OPERATION_STATUS_WAIT);
			operation.setUuid(accountUuid);
			// 持久化指令
			Result operResult = operationService.addOperation(operation.getMsgId(), operation);
			if (operResult.getCode() != 200) {// 持久化失败
				LogUtils.error("持久化指令失败,msg:" + operResult.getMsg());
				return operResult;
			}
			boolean isPushed = pushToDevice(operation);
			if (isPushed) {
				operation.setStatus(IcarConst.OPERATION_STATUS_SUCCESS);
				updateOperationByMsgId(operation.getMsgId(), operation);
				LogUtils.info("推送成功," + operation.getExt());
				return new Result().ok(operation);
			}
			LogUtils.error("推送失败");
		} catch (Exception e) {
			LogUtils.error("执行指令时发生错误,msg:" + e.getMessage());
		}
		operation.setStatus(IcarConst.OPERATION_STATUS_FAIL);
		updateOperationByMsgId(operation.getMsgId(), operation);
		return new Result().serverError("推送失败 ");
	}

	private boolean pushToDevice(Operation operation) {
		return pushService.pushMessageWithAlias(operation.getTuid(), operation.getExt());
	}

	private Result getDefaultDeviceTuidByUuid(String uuid) {
		try {
			Result result = accountService.getAccountByUuid(uuid);
			if (result.getCode() != 200) {// 账户不存在或异常
				return result;
			}
			Account account = (Account) result.getData();
			String defaultTuid = account.getDefaultDevice();
			if (StringUtils.isNull(defaultTuid)) {// 没有默认设备
				return new Result().noRecord("没有默认设备");
			}
			return new Result().ok(defaultTuid);
		} catch (Exception e) {
			LogUtils.error("getDefaultDeviceTuidByUuid异常,msg:" + e.getMessage());
			return new Result().serverError("服务器异常");
		}
	}

	public Result updateOperationByMsgId(String msgId, Operation operation) {
		return operationService.updateOperationByMsgId(msgId, operation);
	}
}

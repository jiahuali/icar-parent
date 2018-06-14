package com.changan.icar.wechat.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.json.JSON;
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
import com.google.gson.Gson;

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
		if (operation == null || StringUtils.isNull(accountUuid)) {
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
			Result operResult = addOperationByUuid(accountUuid, operation);
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
		return pushService.pushMessageWithAlias(operation.getTuid(), new Gson().toJson(operation));
	}

	@Override
	public Result push2DeviceByTuid(String msgId, String tuid, String openId) {
		try {
			if (StringUtils.isNull(msgId, tuid, openId))
				return new Result().paramsNull("参数错误");
			Result operationResult = getOperationByMsgId(msgId);
			if (operationResult.getCode() != 200) {
				return operationResult;
			}
			Operation operation = (Operation) operationResult.getData();

			Result deviceResult = getDefaultDeviceTuidByUuid(openId);
			if (deviceResult.getCode() != 200)
				return deviceResult;
			String defaultTuid = (String) deviceResult.getData();
			if (!defaultTuid.equals(tuid)) {// tuid与默认设备不匹配
				return new Result().error(Result.PUSH_FAILED, "您已更换默认设备，请重新发起操作");
			}
			// String content =
			// GsonUtil.getExculdeWithoutExpose().toJson(operationCustom);
			// if (pushService.pushMessageWithAlias(tuid, content))
			// return new Result().ok();
			// return new Result().pushFailed("推送失败");
			boolean pushToDevice = pushToDevice(operation);
			if (pushToDevice) {
				return new Result().ok(null);
			}
			return new Result().error(Result.PUSH_FAILED, "推送失败");
		} catch (Exception e) {
			LogUtils.error("根据msgId推送指令时发生错误" + e.getMessage());
			return new Result().serverError("服务器异常");
		}

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

	@Override
	public Result updateOperationByMsgId(String msgId, Operation operation) {
		return operationService.updateOperationByMsgId(msgId, operation);
	}

	public Result updateOperationByMsgId(String openId, String msgId, String nickName, String headImgUrl,
			Double longitude, Double latitude) {
		try {
			if (StringUtils.isNull(openId, msgId, nickName, headImgUrl) || longitude == null || latitude == null)
				return new Result().paramsNull("参数为空");
			Map<String, String> params = new HashMap<>();
			params.put("msgId", msgId);
			Map<String, Object> ext = new HashMap<>();
			ext.put("longitude", longitude);
			ext.put("latitude", latitude);
			ext.put("nickName", nickName);
			ext.put("headImgUrl", headImgUrl);
			Operation operation = new Operation();
			operation.setMsgId(msgId);
			String extJson = JSON.json(ext);
			operation.setExt(extJson);
			Result result = operationService.updateOperationByMsgId(msgId, operation);
			return result;
		} catch (Exception e) {
			LogUtils.error("更新operation时，服务器发生异常,MSG:" + e.getMessage());
			return new Result().serverError("更新operation时，服务器发生异常");
		}
	}

	public Result updateOperationStatusByMsgId(String openId, String msgId, String tuid) {

		LogUtils.info("根据msgId更新指令:msgid:" + msgId);
		try {
			if (StringUtils.isNull(msgId, openId, tuid))
				return new Result().paramsNull("参数不能为空");
			Operation newOperation = new Operation();
			newOperation.setMsgId(msgId);
			newOperation.setTuid(tuid);
			newOperation.setUuid(openId);
			Result operationByMsgId = operationService.getOperationByMsgId(msgId);
			if (operationByMsgId.getCode() != 200) {
				LogUtils.info("查找opertaion失败，msg：" + operationByMsgId.getMsg());
				return operationByMsgId;
			}
			Operation operation = (Operation) operationByMsgId.getData();
			if (operation.getOperation() == IcarConst.OPERATION_OPER_WYJR) {// 更新的是我要接人指令
				if (operation.getStatus() == IcarConst.OPERATION_STATUS_WAIT) {
					newOperation.setStatus(IcarConst.OPERATION_STATUS_SUCCESS);
				} else {
					LogUtils.error("指令已完成，请不要重复操作");
					return new Result().error(Result.ALREADY_USED, "指令已完成，请不要重复操作");
				}
			}
			Result updateResult = operationService.updateOperationByMsgId(msgId, newOperation);
			if (updateResult.getCode() == 200) {
				return new Result().ok(openId);
			} else {
				return updateResult;
			}
		} catch (Exception e) {
			LogUtils.error("根据msgId更新指令时发生错误" + e.getMessage());
			return new Result().serverError("服务器错误");
		}
	}

	@Override
	public Result getOperationByMsgId(String msgId) {
		if (StringUtils.isNull(msgId)) {
			return new Result().paramsNull("参数不能为空");
		}
		try {
			return operationService.getOperationByMsgId(msgId);
		} catch (Exception e) {
			LogUtils.error("查询指令时，服务器发生异常," + e.getMessage());
			return new Result().serverError("服务器发生异常");
		}

	}

	@Override
	public Result addOperationByUuid(String uuid, Operation operation) {
		try {
			// 设置操作的时间，以服务器为准
			operation.setDatetime(new Date());
			// // 设置操作的设备
			if (operation.getOperation() == IcarConst.OPERATION_OPER_WYJR) {
				Result result = getDefaultDeviceTuidByUuid(uuid);
				if (result.getCode() != 200) {
					LogUtils.error("用户没有默认设备");
					return result;
				}
				// 设置tuid
				String tuid = (String) result.getData();
				operation.setTuid(tuid);
			}
			// 设置操作的账号UUID
			operation.setUuid(uuid);
			// 生成操作的UUID
			operation.setMsgId(StringUtils.generateUuid());
			// 生成操作的状态
			if (operation.getType() == IcarConst.OPERATION_TYPE_NONERESP_REQUEST) {// 非响应式请求不需要设备回馈，默认为已完成
				operation.setStatus(IcarConst.OPERATION_STATUS_SUCCESS);
			} else if (operation.getType() == IcarConst.OPERATION_TYPE_REQUEST) {// 请求
				operation.setStatus(IcarConst.OPERATION_STATUS_WAIT);
			} else {// 响应
				// TODO 定义响应
				operation.setStatus(IcarConst.OPERATION_STATUS_SUCCESS);
			}
			return operationService.addOperation(operation.getMsgId(), operation);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.error("初始化指令时发生错误" + e.getMessage());
			return new Result().serverError("服务器异常");
		}
	}
}

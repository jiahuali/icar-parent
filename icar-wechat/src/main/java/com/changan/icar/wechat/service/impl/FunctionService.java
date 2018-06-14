package com.changan.icar.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.config.annotation.Reference;
import com.changan.icar.comm.dto.IcarConst;
import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Operation;
import com.changan.icar.comm.service.OperationService;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;

@Service
public class FunctionService {
	@Reference(version = "1.0.0")
	private OperationService operationService;

	public Result updateOperationByMsgId(String openId, String msgId, String nickName, String headImgUrl,
			Double longitude, Double latitude) {
		if (StringUtils.isNull(openId, msgId, nickName, headImgUrl) || longitude == null || latitude == null)
			return new Result().paramsNull("参数为空");
		try {
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
			if (result.getCode() == 200) {
				Operation operationTmp = (Operation) operationService.getOperationByMsgId(msgId).getData();
				result.setData(operationTmp.getUuid());
			}
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
//			newOperation.setUuid(openId);
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
				return new Result().ok(operation.getUuid());
			} else {
				return updateResult;
			}
		} catch (Exception e) {
			LogUtils.error("根据msgId更新指令时发生错误" + e.getMessage());
			return new Result().serverError("服务器错误");
		}
	}

	// public Result executeByOpenId(String openId, Integer operation) {
	// if (StringUtils.isNull(openId)) {
	// return new Result().paramsNull("参数不能为空");
	// }
	// Operation opeartionObj = new Operation();
	// opeartionObj.setType(IcarConst.OPERATION_TYPE_REQUEST);
	// opeartionObj.setOperation(operation);
	// operationService
	// try {
	// Result result = null;
	// // 找人接我操作
	// if (opeartionObj.getOperation() == IcarConst.OPERATION_OPER_ZRJW) {
	// Result accResult = accountService.getAccountByOpenId(openId);
	// if (accResult.getCode() != 200)
	// return accResult;
	// Device device = new Device();
	// device.setUuid(openId);
	// result = execute(device, operation);
	// } else {// 我要接人
	// Result deviceResult = deviceService.getDefaultDeviceByOpenId(openId);
	// if (deviceResult.getCode() != 200) {
	// LogUtils.error("查询设备失败," + deviceResult.getCode() + ":" +
	// deviceResult.getMsg());
	// return deviceResult;
	// }
	// // 找到了默认设备
	// Device device = (Device) deviceResult.getData();
	// result = execute(device, operation);
	// // 关机状态在data中提示
	// if (device.getStatus() == IcarConst.DEVICE_STATUS_OFFLINE) {
	// // TODO
	// }
	// }
	// return result;
	// } catch (Exception e) {
	// LogUtils.error("通过openId执行指令时发生错误" + e.getMessage());
	// return new Result().serverError("服务器异常");
	// }
	// }
}

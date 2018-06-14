package com.changan.icar.wechat.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Operation;

public interface OperationService {
	Result execute(String accountUuid, Operation operation);

	Result updateOperationByMsgId(String msgId, Operation operation);

	Result getOperationByMsgId(String msgId);

	Result push2DeviceByTuid(String msgId, String tuid, String openId);

	Result addOperationByUuid(String uuid, Operation operation);
}

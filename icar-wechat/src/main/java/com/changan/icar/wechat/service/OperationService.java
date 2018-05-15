package com.changan.icar.wechat.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Operation;

public interface OperationService {
	Result execute(String accountUuid, Operation operation);
}

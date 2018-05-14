package com.changan.icar.comm.service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Operation;
import com.changan.icar.comm.po.OperationExample;

/**
 * 操作服务
 * 
 * @author ljh
 *
 */
public interface OperationService {

	Result addOperation(String msgId, Operation operation);

	Result updateOperationByMsgId(String msgId, Operation operation);

	Result listOperationByTuid(String tuid, Integer pageSize, Integer pages);

	Result getOperationByMsgId(String msgId);

	Result listOperationByExample(OperationExample example, Integer pageSize, Integer pages);

}

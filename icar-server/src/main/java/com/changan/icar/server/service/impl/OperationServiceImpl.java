package com.changan.icar.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Operation;
import com.changan.icar.comm.po.OperationExample;
import com.changan.icar.comm.service.OperationService;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.server.dao.OperationMapper;

@Service
public class OperationServiceImpl implements OperationService {
	@Autowired
	private OperationMapper operationMapper;

	@Override
	public Result addOperation(String msgId, Operation operation) {
		LogUtils.info("请求添加操作,msgId:" + msgId);
		if (StringUtils.isNull(msgId)) {
			LogUtils.error("添加指令必须有msgId");
			return new Result().paramsNull("添加指令必须有msgId");
		}
		if (operation == null) {
			LogUtils.error("添加指令必须携带指令相关内容");
			return new Result().paramsNull("指令内容为空");
		}
		// 多重保障
		operation.setMsgId(msgId);
		LogUtils.info("指令内容:" + operation.toString());
		try {
			int rows = operationMapper.insertSelective(operation);
			if (rows > 0) {
				LogUtils.info("插入operation成功");
				return new Result().ok(operation);
			} else {
				LogUtils.info("插入operation失败");
			}
		} catch (Exception e) {
			LogUtils.info("插入operation发生异常,信息:" + e.getMessage());
		}
		return new Result().serverError("添加operation失败");
	}

	@Override
	public Result updateOperationByMsgId(String msgId, Operation operation) {
		LogUtils.info("请求更新operation，msgId：" + msgId);
		if (StringUtils.isNull(msgId) || operation == null) {
			LogUtils.error("更新时必须携带msgId以及要更新的参数");
			return new Result().paramsNull("更新时必须携带msgId以及要更新的参数");
		}
		operation.setMsgId(msgId);
		LogUtils.info("要更新的operation:" + operation.toString());
		try {
			int rows = operationMapper.updateByPrimaryKeySelective(operation);
			if (rows == 1) {
				LogUtils.info("更新成功:" + operation.toString());
				return new Result().ok(null);
			}
		} catch (Exception e) {
			LogUtils.error("更新operation时发生异常,msg:" + e.getMessage());
		}
		LogUtils.error("更新失败：" + operation.toString());
		return new Result().serverError("更新operation时发生错误");
	}

	@Override
	public Result listOperationByTuid(String tuid, Integer pageSize, Integer pages) {
		if (StringUtils.isNull(tuid)) {
			return new Result().paramsNull("tuid不能为空");
		}
		OperationExample example = new OperationExample();
		example.createCriteria().andTuidEqualTo(tuid);
		return listOperationByExample(example, pageSize, pages);
	}

	@Override
	public Result listOperationByExample(OperationExample example, Integer pageSize, Integer pages) {
		if (example == null) {
			example = new OperationExample();
		}
		LogUtils.info("查询operationList，" + example);
		try {
			List<Operation> operationList = operationMapper.selectByExampleWithBLOBs(example);
			if (operationList == null || operationList.size() == 0) {
				return new Result().noRecord("未查询到result");
			}
			return new Result().ok(operationList);
		} catch (Exception e) {
			LogUtils.error("listOperationByCriteria发生异常，msg:" + e.getMessage());
			return new Result().serverError("获取operation列表异常");
		}
	}

	@Override
	public Result getOperationByMsgId(String msgId) {
		LogUtils.info("查询operation，msgId:" + msgId);
		if (StringUtils.isNull(msgId)) {
			return new Result().paramsNull("msgId不能为空");
		}
		try {
			Operation operation = operationMapper.selectByPrimaryKey(msgId);
			if (operation != null) {
				LogUtils.info("查询到的operation:" + operation.toString());
				return new Result().ok(operation);
			}
			return new Result().noRecord("未查询到operation");
		} catch (Exception e) {
			LogUtils.error("查询operation发生异常，msg:" + e.getMessage());
			return new Result().serverError("查询operation失败");
		}
	}
}

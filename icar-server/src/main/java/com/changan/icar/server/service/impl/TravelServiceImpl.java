package com.changan.icar.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changan.icar.comm.dto.Result;
import com.changan.icar.comm.po.Travel;
import com.changan.icar.comm.po.TravelExample;
import com.changan.icar.comm.service.TravelService;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;
import com.changan.icar.server.dao.TravelMapper;

@Service
public class TravelServiceImpl implements TravelService {
	@Autowired
	private TravelMapper travelMapper;

	@Override
	public Result addTravelByTuid(String tuid, Travel travel) {
		LogUtils.info("请求添加travel操作,tuid:" + tuid);
		if (StringUtils.isNull(tuid)) {
			LogUtils.error("添加行程必须有tuid");
			return new Result().paramsNull("添加行程必须有tuid");
		}
		if (travel == null) {
			LogUtils.error("添加行程必须携带行程相关内容");
			return new Result().paramsNull("行程内容为空");
		}
		// 多重保障
		travel.setTuid(tuid);
		LogUtils.info("行程内容:" + travel.toString());
		try {
			int rows = travelMapper.insertSelective(travel);
			if (rows > 0) {
				LogUtils.info("插入travel成功");
				return new Result().ok(travel);
			} else {
				LogUtils.info("插入travel失败");
			}
		} catch (Exception e) {
			LogUtils.info("插入travel发生异常,信息:" + e.getMessage());
		}
		return new Result().serverError("添加travel失败");
	}

	@Override
	public Result updateTravelByUuid(String uuid, Travel travel) {
		LogUtils.info("请求更新travel，：" + uuid);
		if (StringUtils.isNull(uuid) || travel == null) {
			LogUtils.error("更新时必须携带uuid以及要更新的参数");
			return new Result().paramsNull("更新时必须携带uuid以及要更新的参数");
		}
		travel.setUuid(uuid);
		LogUtils.info("要更新的travel:" + travel.toString());
		try {
			int rows = travelMapper.updateByPrimaryKeySelective(travel);
			if (rows == 1) {
				LogUtils.info("更新成功:" + travel.toString());
				return new Result().ok(null);
			}
		} catch (Exception e) {
			LogUtils.error("更新travel时发生异常,msg:" + e.getMessage());
		}
		LogUtils.error("更新失败：" + travel.toString());
		return new Result().serverError("更新travel时发生错误");
	}

	@Override
	public Result deleteTravelByUuid(String uuid) {
		LogUtils.info("请求删除行程，uuid:" + uuid);
		if (StringUtils.isNull(uuid)) {
			LogUtils.error("请求删除行程必须有uuid");
			return new Result().paramsNull("删除行程必须提供uuid");
		}
		try {
			int rows = travelMapper.deleteByPrimaryKey(uuid);
			if (rows == 1) {
				return new Result().ok(null);
			}
		} catch (Exception e) {
			return new Result().serverError("发生异常，msg:" + e.getMessage());
		}
		return new Result().serverError("删除时发生未知异常");
	}

	@Override
	public Result listTravelByTuid(String tuid, Integer pageSize, Integer pages) {
		if (StringUtils.isNull(tuid)) {
			return new Result().paramsNull("tuid不能为空");
		}
		TravelExample travelExample = new TravelExample();
		travelExample.createCriteria().andTuidEqualTo(tuid);
		return listTravelByExample(travelExample, pageSize, pages);
	}

	@Override
	public Result listTravelByExample(TravelExample example, Integer pageSize, Integer pages) {
		if (example == null) {
			return new Result().paramsNull("查询的参数不能为空");
		}
		LogUtils.info("查询travelList，" + example);
		try {
			List<Travel> travelList = travelMapper.selectByExample(example);
			if (travelList == null || travelList.size() == 0) {
				return new Result().noRecord("未查询到result");
			}
			return new Result().ok(travelList);
		} catch (Exception e) {
			LogUtils.error("listTravelByExample发生异常，msg:" + e.getMessage());
			return new Result().serverError("获取travelList列表异常");
		}
	}

}

package com.changan.icar.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.annotation.Service;
import com.changan.icar.comm.service.PushService;
import com.changan.icar.comm.util.LogUtils;
import com.changan.icar.comm.util.StringUtils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;

//@org.springframework.stereotype.Service
@Service(version = "1.0.0")
public class PushServiceImpl implements PushService {
	@Autowired
	private JPushClient jPushClient;

	@Override
	public boolean pushMessageWithAlias(String alias, String content) {
		LogUtils.info("请求推送，alias:" + alias + ",content:" + content);
		if (StringUtils.isNull(alias, content)) {
			LogUtils.error("请求推送时，alias和content不能为空");
			return false;
		}
		try {
			PushResult result = jPushClient.sendAndroidMessageWithAlias(null, content, alias);
			LogUtils.info("推送结果:" + result.toString());
			return result.isResultOK();
		} catch (APIConnectionException | APIRequestException e) {
			LogUtils.error("推送时发生错误，" + e.getMessage());
		}
		return false;

	}

	@Override
	public boolean pushMessageAll(String content) {
		LogUtils.info("请求推送全体,content:" + content);
		if (StringUtils.isNull(content)) {
			LogUtils.error("请求推送时，content不能为空");
			return false;
		}
		try {
			PushResult result = jPushClient.sendMessageAll(content);
			LogUtils.info("推送结果:" + result.toString());
			return result.isResultOK();
		} catch (APIConnectionException | APIRequestException e) {
			LogUtils.error("推送时发生错误，" + e.getMessage());
		}
		return false;
	}
}

@Configuration
class PushConfig {
	@Value("${jpush.masterSecret}")
	private String masterSecret;
	@Value("${jpush.appKey}")
	private String appKey;

	@Bean
	public JPushClient getPushClient() {
		JPushClient client = new JPushClient(masterSecret, appKey);
		return client;
	}
}

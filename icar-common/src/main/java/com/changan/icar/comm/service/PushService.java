package com.changan.icar.comm.service;

/**
 * 推送服务
 * 
 * @author ljh
 *
 */
public interface PushService {
	/**
	 * 向指定的alias推送消息
	 * 
	 * @param alias
	 * @param content
	 * @return
	 */
	boolean pushMessageWithAlias(String alias, String content);

	/**
	 * 向全体推送消息
	 * 
	 * @param content
	 * @return
	 */
	boolean pushMessageAll(String content);

}

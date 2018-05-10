package com.changan.icar.comm.dto;

public class IcarConst {

	/**
	 * 导航指令
	 * 
	 */
	public static final int OPERATION_OPER_NAVI = 1;

	/**
	 * 设备类型车机
	 * 
	 */
	public static final int DEVICE_TYPE_TU = 0;

	/**
	 * 设备类型app
	 * 
	 */
	public static final int DEVICE_TYPE_APP = 1;

	/**
	 * 我要接人指令
	 * 
	 */
	public static final int OPERATION_OPER_WYJR = 2;

	/**
	 * 找人接我指令
	 * 
	 */
	public static final int OPERATION_OPER_ZRJW = 3;

	/**
	 * 请求
	 */
	public static final int OPERATION_TYPE_REQUEST = 1;
	/**
	 * 响应
	 */
	public static final int OPERATION_TYPE_RESPONSE = 2;
	/**
	 * 非响应式请求，此种情况请将指令状态设置为完成
	 */
	public static final int OPERATION_TYPE_NONERESP_REQUEST = 3;
	/**
	 * 指令等待完成
	 */
	public static final int OPERATION_STATUS_WAIT = 1;
	/**
	 * 指令已经完成
	 */
	public static final int OPERATION_STATUS_SUCCESS = 2;
	/**
	 * 指令失败
	 */
	public static final int OPERATION_STATUS_FAIL = 3;

	/**
	 * 账号状态正常
	 */
	public static final Integer ACCOUNT_STATUS_OK = 1;

	/**
	 * 账号关闭
	 */
	public static final Integer ACCOUNT_STATUS_CLOSED = 2;

	/**
	 * 账号状态异常
	 */
	public static final Integer ACCOUNT_STATUS_ERROR = 3;

	/**
	 * 普通会员，关注公众号但未绑定车机
	 */
	public static final Integer ACCOUNT_TYPE_COMMON = 1;
	/**
	 * 注册会员，绑定了车机
	 */

	public static final Integer ACCOUNT_TYPE_REG = 2;
	/**
	 * 活跃用户，六个月内发表意见或商城安装功能的
	 */
	public static final Integer ACCOUNT_TYPE_ACTIVE = 3;

	/**
	 * 来自客户端注册的用户
	 */
	public static final Integer ACCOUNT_FROM_CLIENT = 1;
	/**
	 * 来自微信端注册的用户
	 */
	public static final Integer ACCOUNT_FROM_WX = 2;

	public static final int DEVICE_STATUS_ONLINE = 0;

	public static final int DEVICE_STATUS_OFFLINE = 1;

	public static final Integer DEVICE_DEFAULT_YES = 1;

	public static final Integer DEVICE_DEFAULT_NO = 0;
	/**
	 * 用户的回复
	 */
	public static final Integer FEEDBACK_FROM_USER = 1;

	/**
	 * 来自普通管理员回复
	 */
	public static final Integer FEEDBACK_FROM_ADMIN_COMMON = 0;

	/**
	 * 待审核
	 */
	public static final Integer IDEA_STATUS_UNCHECKED = 1;

	/**
	 * 开发中
	 */
	public static final Integer IDEA_STATUS_DEVELOPING = 2;

	/**
	 * 开发完成
	 */
	public static final Integer IDEA_STATUS_DEVELOPED = 3;
	/**
	 * 已关闭
	 */
	public static final Integer IDEA_STATUS_CLOSED = 4;

	/**
	 * 等待回复
	 */
	public static final Integer IDEA_STATUS_WAIT_FEEDBACK = 5;

	/**
	 * 默认类型
	 */
	public static final Integer IDEA_TYPE_DEFAULT = 1;

}

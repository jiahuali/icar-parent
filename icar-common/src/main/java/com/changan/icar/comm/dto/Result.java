package com.changan.icar.comm.dto;

import java.io.Serializable;

public class Result implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2222;

	/**
	 * 200：请求成功，并得到正确结果 500：请求成功，但服务器出现异常错误 400：错误的请求格式
	 */
	private int code;

	private String msg;

	private Object data;

	public Result() {

	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

	/**
	 * 服务器返回正常
	 * 
	 * @param data
	 * @return
	 */
	public Result ok(Object data) {
		this.data = data;
		this.code = SUCCESS;
		this.msg = "";
		return this;
	}

	/**
	 * 服务器异常 ,code为500
	 * 
	 * @param data
	 * @return
	 */
	public Result serverError(String msg) {
		this.code = SERVER_ERROR;
		this.msg = msg;
		return this;
	}

	/**
	 * 参数为空
	 * 
	 * @param data
	 * @return
	 */
	public Result paramsNull(String msg) {
		this.code = PARAMS_NULL;
		this.msg = msg;
		return this;
	}

	/**
	 * 参数错误
	 * 
	 * @param msg
	 * @return
	 */
	public Result paramError(String msg) {
		this.code = PARAMS_ERROR;
		this.msg = msg;
		return this;
	}

	public int getCode() {
		return code;
	}

	public Result setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Result setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 推送为空
	 */
	public static final int PUSH_FAILED = 99;

	/**
	 * 参数为空
	 */
	public static final int PARAMS_NULL = 1000;

	/**
	 * 参数错误
	 */
	public static final int PARAMS_ERROR = 1009;

	/**
	 * 服务器发生异常
	 */
	public static final int SERVER_ERROR = 500;

	/**
	 * 请求成功并结果正确
	 */
	public static final int SUCCESS = 200;

	/**
	 * 要插入的数据已存在
	 * 
	 * @return
	 */
	public static final int ALREAY_ATTACHED = 1001;

	/**
	 * 未查询到记录
	 */
	public static final int NO_RECORD = 1002;

	/**
	 * 要插入的数据已存在
	 * 
	 * @return
	 */
	public static final int ALREAY_BIND = 1003;

	/**
	 * 插入失败，重试
	 * 
	 * @return
	 */
	public static final int INSERT_FAILED = 1004;

	/**
	 * 更新失败，重试
	 * 
	 * @return
	 */
	public static final int UPDATE_FAILED = 1005;

	/**
	 * 设备关机
	 * 
	 * @return
	 */
	public static final int DEVICE_OFFLINE = 1008;

	/**
	 * 设备关机
	 * 
	 * @return
	 */
	public static final int DELETE_FAILED = 1010;

	/**
	 * 指令已被消费
	 * 
	 * @return
	 */
	public static final int ALREADY_USED = 1011;

	/**
	 * 登录失败
	 * 
	 * @return
	 */
	public static final int LOGIN_FAILED = 1012;

}

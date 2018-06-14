package com.changan.icar.comm.util;

import java.util.UUID;

/**
 * String工具类
 * 
 * @author ljh
 *
 */
public class StringUtils {
	/**
	 * String为null或者去除空格后为空串
	 * 
	 * @param str,不定参数支持
	 * @return
	 */
	public static boolean isNull(String... str) {
		for (String string : str) {
			if (string == null || string.trim().equals(""))
				return true;
		}
		return false;
	}

	public static String generateUuid() {
		String uuid = UUID.randomUUID().toString().replace("-", "").trim();
		if (uuid == null || uuid.equals("")) {
			LogUtils.error("生成uuid失败");
			return null;
		}

		return uuid;
	}

}

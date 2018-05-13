package com.changan.icar.comm.util;

import org.apache.log4j.Logger;

public class LogUtils {
	private static Logger log = Logger.getLogger(LogUtils.class);

	public static void info(String msg) {
		log.info(msg);
	}

	public static void error(String msg) {
		log.error(msg);
	}

	public static void warn(String msg) {
		log.warn(msg);
	}
}

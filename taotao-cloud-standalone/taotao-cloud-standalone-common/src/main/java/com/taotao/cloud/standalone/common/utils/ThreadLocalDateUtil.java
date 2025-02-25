package com.taotao.cloud.standalone.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Classname ThreadLocalDateUtil
 * @Description TODO
 * @Author shuigedeng
 * @since 2019-09-24 09:54
 * 
 */
public class ThreadLocalDateUtil {

	public static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		}
	};


	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String nowStr = now.format(format);
		System.out.println(nowStr);
	}


}

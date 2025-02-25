package com.taotao.cloud.demo.utils;


import com.taotao.cloud.common.utils.lang.StringUtil;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StringTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		long startNs1 = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			System.out.println(StringUtil.getUUID());
		}
		long tookMs1 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs1);
		long startNs2 = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			System.out.println(UUID.randomUUID().toString());
		}
		long tookMs2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs2);
		System.out.println("time1:" + tookMs1);
		System.out.println("time2:" + tookMs2);
	}
}

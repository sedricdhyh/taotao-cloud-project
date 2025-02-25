package com.taotao.cloud.common.serializer;

import java.io.IOException;

public interface Serializer {

	/**
	 * 序列化标识名称
	 */
	String name();

	/**
	 * 序列化成字节数组
	 *
	 * @param data
	 */
	byte[] serialize(Object data) throws IOException;

	/**
	 * 反序列化成对象,需要知道是哪个类加载器
	 *
	 * @param bytes
	 * @param classLoader
	 */
	Object deserialize(byte[] bytes, ClassLoader classLoader)
		throws IOException, ClassNotFoundException;
}

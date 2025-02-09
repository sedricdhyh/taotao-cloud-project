package com.taotao.cloud.standalone.common.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Classname FastJsonRedisSerializer
 * @Description TODO
 * @Author shuigedeng
 * @since 2019-07-19 09:27
 *
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

	private final Class<T> clazz;

	public FastJsonRedisSerializer(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	static {
		ParserConfig.getGlobalInstance().addAccept("com.xd.pre.modules.security.social");//解决fastJson autoType is not support错误
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
	}

	@Override
	public byte[] serialize(T t) throws SerializationException {
		if (t == null) {
			return new byte[0];
		}

		return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if (bytes == null || bytes.length <= 0) {
			return null;
		}
		String str = new String(bytes, DEFAULT_CHARSET);
		return JSON.parseObject(str, clazz);
	}
}

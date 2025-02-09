/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding Copyright (c) ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.logger.serialization;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * String encoding defaults to UTF8 and can be customized by setting the property
 * key.serializer.encoding, value.serializer.encoding or serializer.encoding. The first two take
 * precedence over the last.
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:31:49
 */
public class ObjectSerializer implements Serializer<Object> {

	private String encoding = StandardCharsets.UTF_8.name();

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		String propertyName = isKey ? "key.serializer.encoding" : "value.serializer.encoding";
		Object encodingValue = configs.get(propertyName);
		if (encodingValue == null) {
			encodingValue = configs.get("serializer.encoding");
		}
		if (encodingValue instanceof String) {
			encoding = (String) encodingValue;
		}
	}

	@Override
	public byte[] serialize(String topic, Object data) {
		try {
			if (data == null) {
				return null;
			} else {
				if (data instanceof byte[]) {
					String s = new String((byte[]) data);
					return s.getBytes(StandardCharsets.UTF_8);
				} else {
					return data.toString().getBytes(encoding);
				}
			}
		} catch (UnsupportedEncodingException e) {
			throw new SerializationException(
				"Error when serializing string to byte[] due to unsupported encoding "
					+ encoding);

		}
	}
}

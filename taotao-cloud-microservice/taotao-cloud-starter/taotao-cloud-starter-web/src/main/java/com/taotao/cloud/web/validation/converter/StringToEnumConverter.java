/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.web.validation.converter;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.taotao.cloud.common.enums.BaseEnum;
import java.util.Map;
import org.springframework.core.convert.converter.Converter;

/**
 * String枚举转化器
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 22:12:22
 */
public class StringToEnumConverter<T extends BaseEnum> implements Converter<String, T> {

	/**
	 * enumMap
	 */
	private final Map<String, T> enumMap = MapUtil.newHashMap();

	/**
	 * StringToEnumConverter
	 *
	 * @param enumType enumType
	 * @author shuigedeng
	 * @since 2021-09-02 22:12:33
	 */
	public StringToEnumConverter(Class<T> enumType) {
		T[] enums = enumType.getEnumConstants();
		for (T e : enums) {
			Integer code = e.getCode();
			enumMap.put(e.getNameByCode(code), e);
		}
	}

	@Override
	public T convert(String source) {
		T t = enumMap.get(source);
		if (ObjectUtil.isNull(t)) {
			throw new IllegalArgumentException("无法匹配对应的类型");
		}
		return t;
	}
}

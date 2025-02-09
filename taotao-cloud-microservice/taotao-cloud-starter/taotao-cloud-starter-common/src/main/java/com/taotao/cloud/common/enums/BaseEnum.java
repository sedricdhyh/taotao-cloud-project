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
package com.taotao.cloud.common.enums;

/**
 * 通用枚举接口
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 19:30:28
 */
public interface BaseEnum {

	/**
	 * 获取枚举编码
	 *
	 * @return 枚举编码
	 * @since 2021-09-02 19:30:35
	 */
	int getCode();

	/**
	 * 获取枚举描述
	 *
	 * @return 枚举描述
	 * @since 2021-09-02 19:30:35
	 */
	String getDesc();

	/**
	 * 通过枚举编码获取枚举名称
	 *
	 * @param code 枚举编码
	 * @return 获取枚举名称
	 * @since 2021-09-02 19:30:48
	 */
	String getNameByCode(int code);
}


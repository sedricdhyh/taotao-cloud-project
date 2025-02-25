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
 * token角色类型
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-22 10:47:30
 */
public enum UserEnum implements BaseEnum {
	/**
	 * 会员
	 */
	MEMBER(1, "会员"),
	/**
	 * 商家
	 */
	STORE(2, "商家"),
	/**
	 * 管理员
	 */
	MANAGER(3, "管理员"),
	/**
	 * 系统
	 */
	SYSTEM(4, "系统");

	private final int code;
	private final String desc;

	UserEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public String getNameByCode(int code) {
		for (UserEnum result : UserEnum.values()) {
			if (result.getCode() == code) {
				return result.name().toLowerCase();
			}
		}
		return null;
	}

	@Override
	public int getCode() {
		return code;
	}

	public static String getByCode(int code) {
		for (UserEnum result : UserEnum.values()) {
			if (result.getCode() == code) {
				return result.name().toLowerCase();
			}
		}
		return null;
	}

	public static UserEnum getEnumByCode(int code) {
		for (UserEnum result : UserEnum.values()) {
			if (result.getCode() == code) {
				return result;
			}
		}
		return null;
	}

}

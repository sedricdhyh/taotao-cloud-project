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


import com.taotao.cloud.common.constant.RuleConstants;
import com.taotao.cloud.common.constant.ValidatorConstants;

/**
 * 参数校验错误
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-22 10:46:32
 */
public enum ValidatorExceptionEnum implements BaseEnum {

	/**
	 * Parameter传参，请求参数缺失异常
	 */
	MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION(RuleConstants.USER_OPERATION_ERROR_TYPE_CODE
		+ ValidatorConstants.VALIDATOR_EXCEPTION_STEP_CODE + "01",
		"Parameter传参，请求参数缺失异常，参数名：{}，类型为：{}"),

	/**
	 * 请求数据经过httpMessageConverter出错
	 */
	HTTP_MESSAGE_CONVERTER_ERROR(RuleConstants.USER_OPERATION_ERROR_TYPE_CODE
		+ ValidatorConstants.VALIDATOR_EXCEPTION_STEP_CODE + "02", "请求Json数据格式错误或Json字段格式转化问题"),

	/**
	 * 不受支持的媒体类型
	 */
	HTTP_MEDIA_TYPE_NOT_SUPPORT(RuleConstants.USER_OPERATION_ERROR_TYPE_CODE
		+ ValidatorConstants.VALIDATOR_EXCEPTION_STEP_CODE + "03", "请求的http media type不合法"),

	/**
	 * 不受支持的http请求方法
	 */
	HTTP_METHOD_NOT_SUPPORT(RuleConstants.USER_OPERATION_ERROR_TYPE_CODE
		+ ValidatorConstants.VALIDATOR_EXCEPTION_STEP_CODE + "04", "当前接口不支持{}方式请求"),

	/**
	 * 404找不到资源
	 */
	NOT_FOUND(RuleConstants.USER_OPERATION_ERROR_TYPE_CODE
		+ ValidatorConstants.VALIDATOR_EXCEPTION_STEP_CODE + "05", "404：找不到请求的资源"),

	/**
	 * 参数校验失败
	 * <p>
	 * 拦截@Valid和@Validated校验失败返回的错误提示
	 */
	VALIDATED_RESULT_ERROR(RuleConstants.USER_OPERATION_ERROR_TYPE_CODE
		+ ValidatorConstants.VALIDATOR_EXCEPTION_STEP_CODE + "06", "参数校验失败，请检查参数的传值是否正确，具体信息：{}"),

	/**
	 * 数据库字段值唯一性校验出错，参数不完整
	 */
	TABLE_UNIQUE_VALIDATE_ERROR(RuleConstants.USER_OPERATION_ERROR_TYPE_CODE
		+ ValidatorConstants.VALIDATOR_EXCEPTION_STEP_CODE + "07", "数据库字段值唯一性校验出错，具体信息：{}"),

	/**
	 * 验证码为空
	 */
	CAPTCHA_EMPTY(RuleConstants.USER_OPERATION_ERROR_TYPE_CODE
		+ ValidatorConstants.VALIDATOR_EXCEPTION_STEP_CODE + "08", "验证码参数不能为空"),

	/**
	 * 验证码错误
	 */
	CAPTCHA_ERROR(RuleConstants.USER_OPERATION_ERROR_TYPE_CODE
		+ ValidatorConstants.VALIDATOR_EXCEPTION_STEP_CODE + "09", "验证码错误"),

	/**
	 * 数据库唯一性校验错误，sql执行错误
	 */
	UNIQUE_VALIDATE_SQL_ERROR(RuleConstants.USER_OPERATION_ERROR_TYPE_CODE
		+ ValidatorConstants.VALIDATOR_EXCEPTION_STEP_CODE + "10", "数据库唯一性校验错误，sql执行错误，具体信息：{}"),

	/**
	 * 拖拽验证码错误
	 */
	DRAG_CAPTCHA_ERROR(RuleConstants.USER_OPERATION_ERROR_TYPE_CODE
		+ ValidatorConstants.VALIDATOR_EXCEPTION_STEP_CODE + "11", "拖拽验证码错误");

	/**
	 * 错误编码
	 */
	private final String errorCode;

	/**
	 * 提示用户信息
	 */
	private final String userTip;

	ValidatorExceptionEnum(String errorCode, String userTip) {
		this.errorCode = errorCode;
		this.userTip = userTip;
	}

	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getDesc() {
		return errorCode;
	}

	@Override
	public String getNameByCode(int code) {
		for (ValidatorExceptionEnum result : ValidatorExceptionEnum.values()) {
			if (result.getCode() == code) {
				return result.name().toLowerCase();
			}
		}
		return null;
	}

	@Override
	public int getCode() {
		return 0;
	}

	public String getUserTip() {
		return userTip;
	}
}

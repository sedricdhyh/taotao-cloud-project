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
package com.taotao.cloud.web.validation.constraints;

import com.taotao.cloud.web.validation.constraints.EnumValue;
import java.lang.reflect.Method;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * EnumValueValidator 
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-03 08:04:16
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, Integer> {

	private Class<? extends Enum> enumClass;
	private static final String METHOD_NAME = "toEnum";

	@Override
	public void initialize(EnumValue constraintAnnotation) {
		enumClass = constraintAnnotation.value();
		try {
			// 先判断该enum是否实现了toEnum方法
			enumClass.getDeclaredMethod(METHOD_NAME, int.class);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException("the enum class has not toEnum method", e);
		}
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
		Method declareMethod;
		try {
			declareMethod = enumClass.getDeclaredMethod(METHOD_NAME, int.class);
		} catch (NoSuchMethodException e) {
			return false;
		}
		try {
			declareMethod.invoke(null, value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}


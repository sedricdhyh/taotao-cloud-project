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
package com.taotao.cloud.common.utils.spider;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * xml CssQuery
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 20:01:42
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CssQuery {

	/**
	 * CssQuery
	 *
	 * @return CssQuery
	 */
	String value();

	/**
	 * 读取的 dom attr
	 *
	 * <p>
	 * attr：元素对于的 attr 的值 html：整个元素的html text：元素内文本 allText：多个元素的文本值
	 * </p>
	 *
	 * @return attr
	 */
	String attr() default "";

	/**
	 * 正则，用于对 attr value 处理
	 *
	 * @return regex
	 */
	String regex() default "";

	/**
	 * 默认的正则 group
	 */
	int DEFAULT_REGEX_GROUP = 0;

	/**
	 * 正则 group，默认为 0
	 *
	 * @return regexGroup
	 */
	int regexGroup() default DEFAULT_REGEX_GROUP;

	/**
	 * 嵌套的内部模型：默认 false
	 *
	 * @return 是否为内部模型
	 */
	boolean inner() default false;
}

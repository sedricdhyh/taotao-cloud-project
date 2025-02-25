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
package com.taotao.cloud.feign.execption;

import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.common.exception.BaseException;

/**
 * FeignDecodeException
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 20:09:13
 */
public class FeignDecodeException extends BaseException {

	private static final long serialVersionUID = 6610083281801529147L;

	public FeignDecodeException(String message) {
		super(message);
	}

	public FeignDecodeException(Integer code, String message) {
		super(code, message);
	}

	public FeignDecodeException(String message, Throwable e) {
		super(message, e);
	}

	public FeignDecodeException(Throwable e) {
		super(e);
	}

	public FeignDecodeException(Integer code, String message, Throwable e) {
		super(code, message, e);
	}

	public FeignDecodeException(ResultEnum result) {
		super(result);
	}

	public FeignDecodeException(ResultEnum result, Throwable e) {
		super(result, e);
	}
}

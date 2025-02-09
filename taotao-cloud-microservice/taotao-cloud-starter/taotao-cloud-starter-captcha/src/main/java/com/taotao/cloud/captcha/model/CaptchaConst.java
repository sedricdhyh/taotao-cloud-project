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
package com.taotao.cloud.captcha.model;

/**
 * Const 
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-04 07:40:26
 */
public class CaptchaConst {

	/**
	 * 滑块底图路径
	 */
	public static final String ORIGINAL_PATH_JIGSAW = "captcha.captchaOriginalPath.jigsaw";

	/***
	 *点选底图路径
	 */
	public static final String ORIGINAL_PATH_PIC_CLICK = "captcha.captchaOriginalPath.pic-click";

	/**
	 * 缓存local/redis...
	 */
	public static final String CAPTCHA_CACHETYPE = "captcha.cacheType";

	/**
	 * 右下角水印文字(我的水印)
	 */
	public static final String CAPTCHA_WATER_MARK = "captcha.water.mark";

	/**
	 * 点选文字验证码的文字字体(宋体)
	 */
	public static final String CAPTCHA_FONT_TYPE = "captcha.font.type";

	/**
	 * 验证码类型default两种都实例化。
	 */
	public static final String CAPTCHA_TYPE = "captcha.type";

	/**
	 * 滑动干扰项(0/1/2)
	 */
	public static final String CAPTCHA_INTERFERENCE_OPTIONS = "captcha.interference.options";

	/**
	 * 底图自定义初始化
	 */
	public static final String CAPTCHA_INIT_ORIGINAL = "captcha.init.original";

	/**
	 * 滑动误差偏移量
	 */
	public static final String CAPTCHA_SLIP_OFFSET = "captcha.slip.offset";

	/**
	 * aes加密开关
	 */
	public static final String CAPTCHA_AES_STATUS = "captcha.aes.status";

	/**
	 * 右下角水印字体(宋体)
	 */
	public static final String CAPTCHA_WATER_FONT = "captcha.water.font";

	/**
	 * local缓存的阈值
	 */
	public static final String CAPTCHA_CACAHE_MAX_NUMBER = "captcha.cache.number";

	/**
	 * 定时清理过期local缓存，秒
	 */
	public static final String CAPTCHA_TIMING_CLEAR_SECOND = "captcha.timing.clear";

	/**
	 * 历史资源清除开关 0禁用,1 开启
	 */
	public static final String HISTORY_DATA_CLEAR_ENABLE = "captcha.history.data.clear.enable";

	/**
	 * 接口限流开关 0禁用 1启用
	 */
	public static final String REQ_FREQUENCY_LIMIT_ENABLE = "captcha.req.frequency.limit.enable";

	/**
	 * get 接口 一分钟请求次数限制
	 */
	public static final String REQ_GET_MINUTE_LIMIT = "captcha.req.get.minute.limit";

	/**
	 * 验证失败后，get接口锁定时间
	 */
	public static final String REQ_GET_LOCK_LIMIT = "captcha.req.get.lock.limit";
	/**
	 * 验证失败后，get接口锁定时间
	 */
	public static final String REQ_GET_LOCK_SECONDS = "captcha.req.get.lock.seconds";

	/**
	 * verify 接口 一分钟请求次数限制
	 */
	public static final String REQ_VALIDATE_MINUTE_LIMIT = "captcha.req.verify.minute.limit";
	/**
	 * check接口 一分钟请求次数限制
	 */
	public static final String REQ_CHECK_MINUTE_LIMIT = "captcha.req.check.minute.limit";

}

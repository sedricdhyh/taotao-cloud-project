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
package com.taotao.cloud.mongodb.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 索引配置
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/5/3 08:00
 */
@RefreshScope
@ConfigurationProperties(prefix = MongodbProperties.PREFIX)
public class MongodbProperties {

	public static final String PREFIX = "taotao.cloud.mongodb";

	/**
	 * mongodb 总开关
	 */
	private boolean enabled = false;
	/**
	 * 是否打印日志
	 */
	private Boolean print = false;
	/**
	 * 是否记录慢查询到数据库中
	 */
	private Boolean slowQuery = false;
	/**
	 * 慢查询最短时间,默认为1000毫秒
	 */
	private Long slowTime = 1000L;

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getPrint() {
		return print;
	}

	public void setPrint(Boolean print) {
		this.print = print;
	}

	public Boolean getSlowQuery() {
		return slowQuery;
	}

	public void setSlowQuery(Boolean slowQuery) {
		this.slowQuery = slowQuery;
	}

	public Long getSlowTime() {
		return slowTime;
	}

	public void setSlowTime(Long slowTime) {
		this.slowTime = slowTime;
	}
}

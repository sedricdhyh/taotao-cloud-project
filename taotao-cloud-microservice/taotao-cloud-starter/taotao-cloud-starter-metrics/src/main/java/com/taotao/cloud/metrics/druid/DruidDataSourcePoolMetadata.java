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

package com.taotao.cloud.metrics.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.jdbc.metadata.AbstractDataSourcePoolMetadata;

/**
 * druid 连接池 pool meta data
 *
 * @author shuigedeng
 * @version 2022.05
 * @since 2022-05-27 21:56:15
 */
public class DruidDataSourcePoolMetadata extends AbstractDataSourcePoolMetadata<DruidDataSource> {

	public DruidDataSourcePoolMetadata(DruidDataSource dataSource) {
		super(dataSource);
	}

	@Override
	public Integer getActive() {
		return getDataSource().getActiveCount();
	}

	@Override
	public Integer getMax() {
		return getDataSource().getMaxActive();
	}

	@Override
	public Integer getMin() {
		return getDataSource().getMinIdle();
	}

	@Override
	public String getValidationQuery() {
		return getDataSource().getValidationQuery();
	}

	@Override
	public Boolean getDefaultAutoCommit() {
		return getDataSource().isDefaultAutoCommit();
	}

}

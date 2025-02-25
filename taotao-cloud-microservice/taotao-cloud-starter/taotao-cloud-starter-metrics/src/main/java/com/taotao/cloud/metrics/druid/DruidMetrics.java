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
import com.alibaba.druid.stat.JdbcConnectionStat;
import com.alibaba.druid.stat.JdbcDataSourceStat;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.binder.BaseUnits;
import io.micrometer.core.instrument.binder.MeterBinder;
import java.util.Collections;
import java.util.Map;

/**
 * druid Metrics
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 20:01:42
 */
public class DruidMetrics implements MeterBinder {

	/**
	 * Prefix used for all Druid metric names.
	 */
	public static final String DRUID_METRIC_NAME_PREFIX = "druid";

	private static final String METRIC_CATEGORY = "name";
	private static final String METRIC_NAME_CONNECT_MAX_TIME =
		DRUID_METRIC_NAME_PREFIX + ".connections.connect.max.time";
	private static final String METRIC_NAME_ALIVE_MAX_TIME =
		DRUID_METRIC_NAME_PREFIX + ".connections.alive.max.time";
	private static final String METRIC_NAME_ALIVE_MIN_TIME =
		DRUID_METRIC_NAME_PREFIX + ".connections.alive.min.time";

	private static final String METRIC_NAME_CONNECT_COUNT =
		DRUID_METRIC_NAME_PREFIX + ".connections.connect.count";
	private static final String METRIC_NAME_ACTIVE_COUNT =
		DRUID_METRIC_NAME_PREFIX + ".connections.active.count";
	private static final String METRIC_NAME_CLOSE_COUNT =
		DRUID_METRIC_NAME_PREFIX + ".connections.close.count";
	private static final String METRIC_NAME_ERROR_COUNT =
		DRUID_METRIC_NAME_PREFIX + ".connections.error.count";
	private static final String METRIC_NAME_CONNECT_ERROR_COUNT =
		DRUID_METRIC_NAME_PREFIX + ".connections.connect.error.count";
	private static final String METRIC_NAME_COMMIT_COUNT =
		DRUID_METRIC_NAME_PREFIX + ".connections.commit.count";
	private static final String METRIC_NAME_ROLLBACK_COUNT =
		DRUID_METRIC_NAME_PREFIX + ".connections.rollback.count";

	private final Map<String, DruidDataSource> druidDataSourceMap;
	private final Iterable<Tag> tags;

	public DruidMetrics(Map<String, DruidDataSource> druidDataSourceMap) {
		this(druidDataSourceMap, Collections.emptyList());
	}

	public DruidMetrics(
		Map<String, DruidDataSource> druidDataSourceMap,
		Iterable<Tag> tags) {
		this.druidDataSourceMap = druidDataSourceMap;
		this.tags = tags;
	}

	@Override
	public void bindTo(MeterRegistry meterRegistry) {
		druidDataSourceMap.forEach((name, dataSource) -> {
			JdbcDataSourceStat dsStats = dataSource.getDataSourceStat();
			JdbcConnectionStat connectionStat = dsStats.getConnectionStat();
			// time
			Gauge.builder(METRIC_NAME_CONNECT_MAX_TIME, connectionStat,
					JdbcConnectionStat::getConnectMillisMax)
				.description("Connection connect max time")
				.tags(tags)
				.tag(METRIC_CATEGORY, name)
				.baseUnit(BaseUnits.MILLISECONDS)
				.register(meterRegistry);
			Gauge.builder(METRIC_NAME_ALIVE_MAX_TIME, connectionStat,
					JdbcConnectionStat::getAliveMillisMax)
				.description("Connection alive max time")
				.tags(tags)
				.tag(METRIC_CATEGORY, name)
				.baseUnit(BaseUnits.MILLISECONDS)
				.register(meterRegistry);
			Gauge.builder(METRIC_NAME_ALIVE_MIN_TIME, connectionStat,
					JdbcConnectionStat::getAliveMillisMin)
				.description("Connection alive min time")
				.tags(tags)
				.tag(METRIC_CATEGORY, name)
				.baseUnit(BaseUnits.MILLISECONDS)
				.register(meterRegistry);
			// count
			Gauge.builder(METRIC_NAME_ACTIVE_COUNT, connectionStat,
					JdbcConnectionStat::getActiveCount)
				.description("Connection active count")
				.tags(tags)
				.tag(METRIC_CATEGORY, name)
				.register(meterRegistry);
			Gauge.builder(METRIC_NAME_CONNECT_COUNT, connectionStat,
					JdbcConnectionStat::getConnectCount)
				.description("Connection connect count")
				.tags(tags)
				.tag(METRIC_CATEGORY, name)
				.register(meterRegistry);
			Gauge.builder(METRIC_NAME_CLOSE_COUNT, connectionStat,
					JdbcConnectionStat::getCloseCount)
				.description("Connection close count")
				.tags(tags)
				.tag(METRIC_CATEGORY, name)
				.register(meterRegistry);
			Gauge.builder(METRIC_NAME_ERROR_COUNT, connectionStat,
					JdbcConnectionStat::getErrorCount)
				.description("Connection error count")
				.tags(tags)
				.tag(METRIC_CATEGORY, name)
				.register(meterRegistry);
			Gauge.builder(METRIC_NAME_CONNECT_ERROR_COUNT, connectionStat,
					JdbcConnectionStat::getConnectErrorCount)
				.description("Connection connect error count")
				.tags(tags)
				.tag(METRIC_CATEGORY, name)
				.register(meterRegistry);
			Gauge.builder(METRIC_NAME_COMMIT_COUNT, connectionStat,
					JdbcConnectionStat::getCommitCount)
				.description("Connecting commit count")
				.tags(tags)
				.tag(METRIC_CATEGORY, name)
				.register(meterRegistry);
			Gauge.builder(METRIC_NAME_ROLLBACK_COUNT, connectionStat,
					JdbcConnectionStat::getRollbackCount)
				.description("Connection rollback count")
				.tags(tags)
				.tag(METRIC_CATEGORY, name)
				.register(meterRegistry);
		});
	}
}

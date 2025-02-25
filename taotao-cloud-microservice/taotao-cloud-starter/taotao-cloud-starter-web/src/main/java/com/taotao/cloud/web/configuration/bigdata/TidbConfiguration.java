package com.taotao.cloud.web.configuration.bigdata;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ConditionalOnProperty(name = "taotao.cloud.data.tidb.enabled", havingValue = "true")
public class TidbConfiguration {

	@ConfigurationProperties("spring.datasource.tidb")
	@Bean(name = "tidbDataSource", destroyMethod = "close")
	@Lazy
	public DruidDataSource tidbDataSource() {
		return DataSourceBuilder.create().type(DruidDataSource.class).build();
	}
}


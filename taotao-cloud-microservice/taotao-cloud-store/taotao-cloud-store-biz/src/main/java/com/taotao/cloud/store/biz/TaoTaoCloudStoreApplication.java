package com.taotao.cloud.store.biz;

import com.taotao.cloud.web.annotation.TaoTaoCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@MapperScan(basePackages = "com.taotao.cloud.store.biz.mapper")
@EnableJpaRepositories(basePackages = "com.taotao.cloud.store.biz.repository.inf")
@TaoTaoCloudApplication
public class TaoTaoCloudStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaoTaoCloudStoreApplication.class, args);
	}

}

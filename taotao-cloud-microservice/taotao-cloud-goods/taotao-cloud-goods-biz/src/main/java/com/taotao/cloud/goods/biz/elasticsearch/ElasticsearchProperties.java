package com.taotao.cloud.goods.biz.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * elasticsearch属性
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:17:57
 */
@Data
@Builder
@Component("elasticsearchProperties")
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "taotao.data.elasticsearch")
public class ElasticsearchProperties {

    /**
     * 请求协议
     */
    @Builder.Default
    private String schema = "https";

    /**
     * 集群名称
     */
    @Builder.Default
    private String clusterName = "elasticsearch";

    /**
     * 集群节点
     */
    @Builder.Default
    @NotNull(message = "集群节点不允许为空")
    private List<String> clusterNodes = new ArrayList<>();

    /**
     * 索引前缀
     */
    private String indexPrefix;

    /**
     * 连接超时时间(毫秒)
     */
    @Builder.Default
    private Integer connectTimeout = 1000;

    /**
     * socket 超时时间
     */
    @Builder.Default
    private Integer socketTimeout = 30000;

    /**
     * 连接请求超时时间
     */
    @Builder.Default
    private Integer connectionRequestTimeout = 500;

    /**
     * 每个路由的最大连接数量
     */
    @Builder.Default
    private Integer maxConnectPerRoute = 10;

    /**
     * 最大连接总数量
     */
    @Builder.Default
    private Integer maxConnectTotal = 30;

    /**
     * 索引配置信息
     */
    @Builder.Default
    private Index index = new Index();

    /**
     * 认证账户
     */
    @Builder.Default
    private Account account = new Account();

    /**
     * 索引配置信息
     */
    @Data
    public static class Index {

        /**
         * 分片数量
         */
        private Integer numberOfShards = 3;

        /**
         * 副本数量
         */
        private Integer numberOfReplicas = 2;

    }

    /**
     * 认证账户
     */
    @Data
    public static class Account {

        /**
         * 认证用户
         */
        private String username;

        /**
         * 认证密码
         */
        private String password;

    }


}

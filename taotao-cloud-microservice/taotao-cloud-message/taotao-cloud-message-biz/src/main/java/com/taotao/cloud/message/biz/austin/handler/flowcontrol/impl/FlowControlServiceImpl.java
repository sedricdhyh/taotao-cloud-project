package com.taotao.cloud.message.biz.austin.handler.flowcontrol.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.google.common.util.concurrent.RateLimiter;
import com.taotao.cloud.message.biz.austin.common.constant.AustinConstant;
import com.taotao.cloud.message.biz.austin.common.domain.TaskInfo;
import com.taotao.cloud.message.biz.austin.common.enums.ChannelType;
import com.taotao.cloud.message.biz.austin.handler.enums.RateLimitStrategy;
import com.taotao.cloud.message.biz.austin.handler.flowcontrol.FlowControlParam;
import com.taotao.cloud.message.biz.austin.handler.flowcontrol.FlowControlService;
import org.springframework.stereotype.Service;

@Service

public class FlowControlServiceImpl implements FlowControlService {

    private static final String FLOW_CONTROL_KEY = "flowControl";

    private static final String FLOW_CONTROL_PREFIX = "flow_control_";

    @ApolloConfig("boss.austin")
    private Config config;


    @Override
    public void flowControl(TaskInfo taskInfo, FlowControlParam flowControlParam) {
        RateLimiter rateLimiter = flowControlParam.getRateLimiter();
        Double rateInitValue = flowControlParam.getRateInitValue();

        double costTime = 0;

        // 对比 初始限流值 与 配置限流值，以 配置中心的限流值为准
        Double rateLimitConfig = getRateLimitConfig(taskInfo.getSendChannel());
        if (rateLimitConfig != null && !rateInitValue.equals(rateLimitConfig)) {
            rateLimiter = RateLimiter.create(rateLimitConfig);
            flowControlParam.setRateInitValue(rateLimitConfig);
            flowControlParam.setRateLimiter(rateLimiter);
        }
        if (RateLimitStrategy.REQUEST_RATE_LIMIT.equals(flowControlParam.getRateLimitStrategy())) {
            costTime = rateLimiter.acquire(1);
        }
        if (RateLimitStrategy.SEND_USER_NUM_RATE_LIMIT.equals(flowControlParam.getRateLimitStrategy())) {
            costTime = rateLimiter.acquire(taskInfo.getReceiver().size());
        }

        if (costTime > 0) {
            log.info("consumer {} flow control time {}",
                    ChannelType.getEnumByCode(taskInfo.getSendChannel()).getDescription(), costTime);
        }
    }

    /**
     * 得到限流值的配置
     * <p>
     * apollo配置样例     key：flowControl value：{"flow_control_40":1}
     * <p>
     * 渠道枚举可看：com.java3y.austin.common.enums.ChannelType
     *
     * @param channelCode
     */
    private Double getRateLimitConfig(Integer channelCode) {
        String flowControlConfig = config.getProperty(FLOW_CONTROL_KEY, AustinConstant.APOLLO_DEFAULT_VALUE_JSON_OBJECT);
        JSONObject jsonObject = JSON.parseObject(flowControlConfig);
        if (jsonObject.getDouble(FLOW_CONTROL_PREFIX + channelCode) == null) {
            return null;
        }
        return jsonObject.getDouble(FLOW_CONTROL_PREFIX + channelCode);
    }
}

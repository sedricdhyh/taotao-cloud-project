package com.taotao.cloud.message.biz.austin.handler.flowcontrol;

import com.taotao.cloud.message.biz.austin.common.domain.TaskInfo;

/**
 * 
 * 流量控制服务
 */
public interface FlowControlService {


    /**
     * 根据渠道进行流量控制
     *
     * @param taskInfo
     * @param flowControlParam
     */
    void flowControl(TaskInfo taskInfo, FlowControlParam flowControlParam);

}

package com.taotao.cloud.message.biz.austin.handler.deduplication.builder;

import com.taotao.cloud.message.biz.austin.common.domain.TaskInfo;
import com.taotao.cloud.message.biz.austin.common.enums.AnchorState;
import com.taotao.cloud.message.biz.austin.common.enums.DeduplicationType;
import com.taotao.cloud.message.biz.austin.handler.deduplication.DeduplicationParam;
import org.springframework.stereotype.Service;


@Service
public class ContentDeduplicationBuilder extends AbstractDeduplicationBuilder implements Builder {

    public ContentDeduplicationBuilder() {
        deduplicationType = DeduplicationType.CONTENT.getCode();
    }

    @Override
    public DeduplicationParam build(String deduplication, TaskInfo taskInfo) {
        DeduplicationParam deduplicationParam = getParamsFromConfig(deduplicationType, deduplication, taskInfo);
        if (deduplicationParam == null) {
           return null;
        }
        deduplicationParam.setAnchorState(AnchorState.CONTENT_DEDUPLICATION);
        return deduplicationParam;

    }
}

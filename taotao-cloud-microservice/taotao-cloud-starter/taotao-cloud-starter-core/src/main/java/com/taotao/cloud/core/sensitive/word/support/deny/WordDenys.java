package com.taotao.cloud.core.sensitive.word.support.deny;

import com.taotao.cloud.common.support.instance.impl.Instances;
import com.taotao.cloud.common.support.pipeline.Pipeline;
import com.taotao.cloud.common.utils.collection.ArrayUtil;
import com.taotao.cloud.core.sensitive.word.api.IWordDeny;

/**
 * 所有拒绝的结果
 */
public final class WordDenys {

    private WordDenys(){}

    /**
     * 责任链
     * @param wordDeny 拒绝
     * @param others 其他
     * @return 结果
     * @since 0.0.13
     */
    public static IWordDeny chains(final IWordDeny wordDeny,
                                   final IWordDeny... others) {
        return new WordDenyInit() {
            @Override
            protected void init(Pipeline<IWordDeny> pipeline) {
                pipeline.addLast(wordDeny);

                if(ArrayUtil.isNotEmpty(others)) {
                    for(IWordDeny other : others) {
                        pipeline.addLast(other);
                    }
                }
            }
        };
    }

    /**
     * 系统实现
     * @return 结果
     * @since 0.0.13
     */
    public static IWordDeny system() {
        return Instances.singleton(WordDenySystem.class);
    }

}

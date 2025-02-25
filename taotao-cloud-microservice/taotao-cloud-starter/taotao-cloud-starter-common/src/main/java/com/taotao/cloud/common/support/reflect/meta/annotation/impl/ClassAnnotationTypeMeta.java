package com.taotao.cloud.common.support.reflect.meta.annotation.impl;


import com.taotao.cloud.common.utils.common.ArgUtil;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 类注解类型元数据
 */
public class ClassAnnotationTypeMeta extends AbstractAnnotationTypeMeta {

    /**
     * 注解信息
     * （1）这里其实没有必要使用 {@link Map} 因为一般注解数量不会太多，只是数组性能反而更好。
     */
    private Annotation[] annotations;

    public ClassAnnotationTypeMeta(Class clazz) {
        ArgUtil.notNull(clazz, "clazz");

        annotations = clazz.getAnnotations();
    }

    @Override
    protected Annotation[] getAnnotations() {
        return annotations;
    }

}

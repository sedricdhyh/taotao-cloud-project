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
package com.taotao.cloud.logger.logRecord.aop;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.taotao.cloud.common.utils.log.LogUtil;
import com.taotao.cloud.logger.logRecord.annotation.OperationLog;
import com.taotao.cloud.logger.logRecord.bean.LogDTO;
import com.taotao.cloud.logger.logRecord.context.LogRecordContext;
import com.taotao.cloud.logger.logRecord.function.CustomFunctionRegistrar;
import com.taotao.cloud.logger.logRecord.service.IOperationLogGetService;
import com.taotao.cloud.logger.logRecord.service.IOperatorIdGetService;
import com.taotao.cloud.logger.logRecord.service.LogService;
import com.taotao.cloud.logger.logRecord.thread.LogRecordThreadPool;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 系统日志方面
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-26 14:49:42
 */
@Aspect
@Component
public class SystemLogAspect {

	/**
	 * 日志记录线程池
	 */
	@Autowired
    private LogRecordThreadPool logRecordThreadPool;

	/**
	 * 日志服务
	 */
	@Autowired(required = false)
    private LogService logService;

	/**
	 * 我操作日志获取服务
	 */
	@Autowired(required = false)
    private IOperationLogGetService iOperationLogGetService;

	/**
	 * 我算符id获得服务
	 */
	@Autowired(required = false)
    private IOperatorIdGetService iOperatorIdGetService;

	/**
	 * 解析器
	 */
	private final SpelExpressionParser parser = new SpelExpressionParser();

	/**
	 * 发现者
	 */
	private final DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();

	/**
	 * 在
	 *
	 * @param pjp pjp
	 * @return {@link Object }
	 * @since 2022-04-26 14:49:42
	 */
	@Around("@annotation(com.taotao.cloud.logger.logRecord.annotation.OperationLog) || @annotation(com.taotao.cloud.logger.logRecord.annotation.OperationLogs)")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        Object result;
        List<LogDTO> logDTOList = new ArrayList<>();
        Method method = getMethod(pjp);
        OperationLog[] annotations = method.getAnnotationsByType(OperationLog.class);

        // 将前置和后置执行分开处理并保证顺序
        Map<OperationLog, LogDTO> logDtoMap = new LinkedHashMap<>();
        for (OperationLog annotation : annotations) {
            logDtoMap.put(annotation, null);
        }

        StopWatch stopWatch = new StopWatch();
        try {
            // 方法执行前
            for (OperationLog annotation : annotations) {
                if (annotation.executeBeforeFunc()) {
                    logDtoMap.put(annotation, resolveExpress(annotation, pjp));
                }
            }
            stopWatch.start();
            result = pjp.proceed();
            stopWatch.stop();
            // 方法执行后
            for (OperationLog annotation : annotations) {
                if (!annotation.executeBeforeFunc()) {
                    logDtoMap.put(annotation, resolveExpress(annotation, pjp));
                }
            }
            // 写入成功执行结果
            logDTOList = new ArrayList<>(logDtoMap.values());
            logDTOList.forEach(logDTO -> {
                logDTO.setSuccess(true);
                logDTO.setReturnStr(JSON.toJSONString(result));
            });
        } catch (Throwable throwable) {
            stopWatch.stop();
            // 方法执行异常后
            for (OperationLog annotation : annotations) {
                if (!annotation.executeBeforeFunc()) {
                    logDtoMap.put(annotation, resolveExpress(annotation, pjp));
                }
            }
            // 写入异常执行结果
            logDTOList = new ArrayList<>(logDtoMap.values());
            logDTOList.forEach(logDTO -> {
                logDTO.setSuccess(false);
                logDTO.setException(throwable.getMessage());
            });
            throw throwable;
        }
        finally {
            // 异步发送消息
            logDTOList.forEach(logDTO -> logRecordThreadPool.getLogRecordPoolExecutor().submit(() -> {
                try {
                    // 记录执行时间
                    logDTO.setExecutionTime(stopWatch.getTotalTimeMillis());
                    // 发送日志本地监听
                    if (iOperationLogGetService != null) {
                        iOperationLogGetService.createLog(logDTO);
                    }
                    // 发送消息管道
                    if (logService != null) {
                        logService.createLog(logDTO);
                    }
                } catch (Throwable throwable) {
	                LogUtil.error("Send logDTO error", throwable);
                }
            }));
            // 清除变量上下文
            LogRecordContext.clearContext();
        }
        return result;
    }

	/**
	 * 解决表达
	 *
	 * @param annotation 注释
	 * @param joinPoint  连接点
	 * @return {@link LogDTO }
	 * @since 2022-04-26 14:49:43
	 */
	public LogDTO resolveExpress(OperationLog annotation, JoinPoint joinPoint) {
        LogDTO logDTO = new LogDTO();
        String bizIdSpel = annotation.bizId();
        String msgSpel = annotation.msg();
        String extraSpel = annotation.extra();
        String operatorIdSpel = annotation.operatorId();
        String bizId = bizIdSpel;
        String msg = msgSpel;
        String extra = extraSpel;
        String operatorId = annotation.operatorId();
        try {
            Object[] arguments = joinPoint.getArgs();
            Method method = getMethod(joinPoint);
            String[] params = discoverer.getParameterNames(method);
            StandardEvaluationContext context = LogRecordContext.getContext();
            CustomFunctionRegistrar.register(context);
            if (params != null) {
                for (int len = 0; len < params.length; len++) {
                    context.setVariable(params[len], arguments[len]);
                }
            }

            // bizId 处理：SpEL解析
            if (StringUtils.isNotBlank(bizIdSpel)) {
                Expression bizIdExpression = parser.parseExpression(bizIdSpel);
                bizId = bizIdExpression.getValue(context, String.class);
            }

            // msg 处理：SpEL解析 默认写入原字符串
            if (StringUtils.isNotBlank(msgSpel)) {
                Expression msgExpression = parser.parseExpression(msgSpel);
                Object msgObj = msgExpression.getValue(context, Object.class);
                msg = msgObj instanceof String ? (String) msgObj : JSON.toJSONString(msgObj, JSONWriter.Feature.WriteMapNullValue);
            }

            // extra 处理：SpEL解析 默认写入原字符串
            if (StringUtils.isNotBlank(extraSpel)) {
                Expression extraExpression = parser.parseExpression(extraSpel);
                Object extraObj = extraExpression.getValue(context, Object.class);
                extra = extraObj instanceof String ? (String) extraObj : JSON.toJSONString(extraObj, JSONWriter.Feature.WriteMapNullValue);
            }

            // operatorId 处理：优先级 注解传入 > 自定义接口实现
            if (iOperatorIdGetService != null) {
                operatorId = iOperatorIdGetService.getOperatorId();
            }
            if (StringUtils.isNotBlank(operatorIdSpel)) {
                Expression operatorIdExpression = parser.parseExpression(operatorIdSpel);
                Object operatorIdObj = operatorIdExpression.getValue(context, Object.class);
                operatorId = operatorIdObj instanceof String ? (String) operatorIdObj : JSON.toJSONString(operatorIdObj, JSONWriter.Feature.WriteMapNullValue);
            }

        } catch (Exception e) {
	        LogUtil.error("OperationLogAspect resolveExpress error", e);
        } finally {
            logDTO.setLogId(UUID.randomUUID().toString());
            logDTO.setBizId(bizId);
            logDTO.setBizType(annotation.bizType());
            logDTO.setTag(annotation.tag());
            logDTO.setOperateDate(new Date());
            logDTO.setMsg(msg);
            logDTO.setExtra(extra);
            logDTO.setOperatorId(operatorId);
        }
        return logDTO;
    }

	/**
	 * get方法
	 *
	 * @param joinPoint 连接点
	 * @return {@link Method }
	 * @since 2022-04-26 14:49:43
	 */
	protected Method getMethod(JoinPoint joinPoint) {
        Method method = null;
        try {
            Signature signature = joinPoint.getSignature();
            MethodSignature ms = (MethodSignature) signature;
            Object target = joinPoint.getTarget();
            method = target.getClass().getMethod(ms.getName(), ms.getParameterTypes());
        } catch (NoSuchMethodException e) {
	        LogUtil.error("OperationLogAspect getMethod error", e);
        }
        return method;
    }
}

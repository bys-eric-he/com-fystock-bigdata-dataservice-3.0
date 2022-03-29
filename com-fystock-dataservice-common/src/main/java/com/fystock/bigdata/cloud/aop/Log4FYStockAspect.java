package com.fystock.bigdata.cloud.aop;


import com.fystock.bigdata.cloud.annotation.Log4FYStock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 服务日志管理切面拦截
 *
 * @author He.Yong
 * @since 2021-04-11 10:13:11
 */
@Aspect
@Service
@Slf4j
public class Log4FYStockAspect {
    /**
     * 定义拦截规则：拦截所有@LogAspect注解的方法。
     */
    @Pointcut("@annotation(com.fystock.bigdata.cloud.annotation.Log4FYStock)")
    public void logPointcut() {
    }

    /**
     * 拦截器具体实现
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("logPointcut()")
    public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        log.info("--------------- 进入 AOP 日志切面处理 --------------- ");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取被拦截的方法
        Method method = signature.getMethod();
        //获取方法注解
        Log4FYStock log4JFStock = method.getAnnotation(Log4FYStock.class);

        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        String[] parameterNames = signature.getParameterNames();

        if (ObjectUtils.isNotEmpty(log4JFStock) && log4JFStock.isWriteLog()) {
            log.info("操作描述 -> " + log4JFStock.value());
            log.info("调用方法 -> " + methodName);
            log.info("参数名称 -> " + Arrays.toString(parameterNames));
            log.info("参数值 -> " + Arrays.toString(joinPoint.getArgs()));
        }

        // 定义返回对象、得到方法需要的参数
        Object result = null;

        try {
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable e) {
            log.error("**************@Around(logPointcut())方法执行环绕通知出错****************", e);
            throw e;
        }

        // 获取执行的方法名
        long endTime = System.currentTimeMillis();

        // 打印耗时的信息
        log.info("---------------- 结束 AOP 日志切面处理 ----------------");

        log.info("耗时：" + (endTime - beginTime) + " 毫秒!");
        return result;
    }
}
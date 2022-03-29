package com.fystock.bigdata.cloud.aop;

import com.fystock.bigdata.cloud.annotation.DataSource;
import com.fystock.bigdata.cloud.datasource.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * 多数据源动态切换切面拦截
 *
 * @author He.Yong
 * @since 2021-06-11 16:53:58
 */
@Slf4j
@Aspect
@Service
public class DataSourceAspect {
    /**
     * 设置切面范围
     */
    @Pointcut("@annotation(com.fystock.bigdata.cloud.annotation.DataSource)")
    public void pointCut() {
    }

    /**
     * 添加数据源上下文
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("-----进入AOP切面动态切换数据源操作-----");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取被拦截的方法
        Method method = signature.getMethod();
        //获取方法注解
        DataSource dataSource = method.getAnnotation(DataSource.class);
        DataSourceContextHolder.setDataSource(dataSource.value().getValue());
    }

    /**
     * 清除数据源上下文
     */
    @After("pointCut()")
    public void doAfter() {
        DataSourceContextHolder.clear();
        log.info("-----结束AOP切面动态切换数据源操作-----");
    }
}
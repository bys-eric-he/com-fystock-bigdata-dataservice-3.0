package com.fystock.bigdata.cloud.annotation;

import java.lang.annotation.*;

/**
 * 日志注解,用于指定要记录日志的方法或类。
 *
 * @author He.Yong
 * @since 2021-04-11 17:13:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Log4FYStock {
    /**
     * 标识日志内容，清晰地描述方法的内容，以便日志中查看
     *
     * @return
     */
    String value() default "";

    /**
     * 是否记录日志
     *
     * @return
     */
    boolean isWriteLog() default true;
}
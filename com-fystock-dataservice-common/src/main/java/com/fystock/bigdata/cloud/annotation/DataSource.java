package com.fystock.bigdata.cloud.annotation;

import com.fystock.bigdata.cloud.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * 自定义的注解 本注解用于切换数据库
 * 可以在 mapper 或者 service 中使用
 * 可以用于方法，也可以用于类 例 @DataSource("dataSourceMsql")
 *
 * @author He.Yong
 * @since 2021-06-11 13:13:11
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    DataSourceEnum value() default DataSourceEnum.bigdataMarketMysql;
}
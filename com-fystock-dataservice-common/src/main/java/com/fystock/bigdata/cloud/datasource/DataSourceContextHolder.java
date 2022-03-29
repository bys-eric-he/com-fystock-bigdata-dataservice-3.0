package com.fystock.bigdata.cloud.datasource;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据源上下文
 *
 * @author He.Yong
 * @since 2021-06-03 11:24:25
 */
@Slf4j
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new InheritableThreadLocal<>();

    /**
     * 设置数据源
     *
     * @param db
     */
    public static void setDataSource(String db) {
        contextHolder.set(db);
        log.info("---->设置当前数据源为: {} <----", db);
    }

    /**
     * 取得当前数据源
     *
     * @return
     */
    public static String getDataSource() {
        return contextHolder.get();
    }

    /**
     * 清除上下文数据源
     */
    public static void clear() {
        contextHolder.remove();
        log.info("---->已清除上下文数据源<----");
    }
}

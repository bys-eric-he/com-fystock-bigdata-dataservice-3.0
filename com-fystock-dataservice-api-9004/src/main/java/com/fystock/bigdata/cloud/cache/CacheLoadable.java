package com.fystock.bigdata.cloud.cache;

/**
 * 抽像将数据加载到缓存的行为
 *
 * @param <T>
 * @author He.Yong
 * @since 2021-06-22 13:32:19
 */
public interface CacheLoadable<T> {
    /**
     * 缓存数据加载方法
     *
     * @return
     */
    T load();
}

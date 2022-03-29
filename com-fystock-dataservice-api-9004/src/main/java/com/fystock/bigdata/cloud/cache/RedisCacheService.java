package com.fystock.bigdata.cloud.cache;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redis缓存模版方法
 *
 * @param <T>
 * @author He.Yong
 * @since 2021-06-22 13:32:19
 */
@Slf4j
@Service
public class RedisCacheService<T> {

    @Autowired
    private RedisCache redisCache;

    /**
     * 根据Key从缓存中获取数据
     *
     * @param key
     * @param cacheLoadable
     * @param clazz
     * @return
     */
    public T getCacheData(String key, long expire, TimeUnit timeUnit, CacheLoadable<T> cacheLoadable, Class<T> clazz) {
        Object value = redisCache.get(key);
        // 命中缓存
        if (value != null) {
            return JSON.parseObject(JSON.toJSONString(JSON.parse(value.toString())), clazz);
        }
        //从数据库中获取数据
        T result = cacheLoadable.load();
        if (result != null) {
            log.error("从DB中获取到数据并存入缓存!->" + JSON.toJSONString(result));
            //加入缓存
            redisCache.set(key, JSON.toJSONString(result), expire, timeUnit);
            return result;
        }
        return null;
    }
}

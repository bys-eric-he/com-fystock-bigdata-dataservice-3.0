package com.fystock.bigdata.cloud.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fystock.bigdata.cloud.annotation.Log4FYStock;
import com.fystock.bigdata.cloud.cache.RedisCache;
import com.fystock.bigdata.cloud.cache.RedisKey;
import com.fystock.bigdata.cloud.mapper.TradeMoneySumInfoMapper;
import com.fystock.bigdata.cloud.mapper.TradeStockCountInfoMapper;
import com.fystock.bigdata.cloud.mapper.TradeUserCountInfoMapper;
import com.fystock.bigdata.cloud.mapping.BigScreenTradeAggInfoMapping;
import com.fystock.bigdata.cloud.model.TradeMoneySumInfo;
import com.fystock.bigdata.cloud.model.TradeStockCountInfo;
import com.fystock.bigdata.cloud.model.TradeUserCountInfo;
import com.fystock.bigdata.cloud.redis.BigScreenTradeAggInfo;
import com.fystock.bigdata.cloud.service.BigScreenTradeAggInfoService;
import com.fystock.bigdata.cloud.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 交易用户数走势、成交笔数走势、交易额走势实时数据
 *
 * @author He.Yong
 * @since 2021-06-17 18:20:19
 */
@Slf4j
@Service
public class BigScreenTradeAggInfoServiceImpl implements BigScreenTradeAggInfoService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TradeStockCountInfoMapper tradeStockCountInfoMapper;

    @Autowired
    private TradeMoneySumInfoMapper tradeMoneySumInfoMapper;

    @Autowired
    private TradeUserCountInfoMapper tradeUserCountInfoMapper;

    /**
     * 获取当前最新交易额走势数据
     *
     * @return
     */
    @Log4FYStock(value = "获取当前最新交易额走势数据")
    @Override
    public Map<String, TradeMoneySumInfo> getTradeMoneySumInfo() {
        //从Flink维护的缓存中获取
        Map<Object, Object> bigScreenTradeAggValues = redisCache.hmget(String.format(RedisKey.BIGSCREEN_ADS_TRADE_AGG, DateTimeUtil.getCurrentDateYYYYMMDD()));
        if (bigScreenTradeAggValues.isEmpty()) {
            return null;
        }
        Map<String, TradeMoneySumInfo> results = new HashMap<>();
        for (Map.Entry<Object, Object> entry : bigScreenTradeAggValues.entrySet()) {
            log.info("-->当前最新交易额走势数据: 键 = " + entry.getKey() + ", 值 = " + entry.getValue());
            BigScreenTradeAggInfo bigScreenTradeAggInfo = objectMapper.convertValue(entry.getValue(), BigScreenTradeAggInfo.class);
            if (bigScreenTradeAggInfo != null) {
                TradeMoneySumInfo tradeMoneySumInfo = BigScreenTradeAggInfoMapping.toTradeMoneySumInfoModel(bigScreenTradeAggInfo);
                results.put(entry.getKey().toString(), tradeMoneySumInfo);
            }
        }

        return results;
    }

    /**
     * 获取当前最新买入、卖出笔数走势数据
     *
     * @return
     */
    @Log4FYStock(value = "获取当前最新买入、卖出笔数走势数据")
    @Override
    public Map<String, TradeStockCountInfo> getTradeStockCountInfo() {
        //从Flink维护的缓存中获取
        Map<Object, Object> bigScreenTradeAggValues = redisCache.hmget(String.format(RedisKey.BIGSCREEN_ADS_TRADE_AGG, DateTimeUtil.getCurrentDateYYYYMMDD()));
        if (bigScreenTradeAggValues.isEmpty()) {
            return null;
        }
        Map<String, TradeStockCountInfo> results = new HashMap<>();
        for (Map.Entry<Object, Object> entry : bigScreenTradeAggValues.entrySet()) {
            log.info("-->当前最新买入、卖出笔数走势数据: 键 = " + entry.getKey() + ", 值 = " + entry.getValue());
            BigScreenTradeAggInfo bigScreenTradeAggInfo = objectMapper.convertValue(entry.getValue(), BigScreenTradeAggInfo.class);
            if (bigScreenTradeAggInfo != null) {
                TradeStockCountInfo tradeStockCountInfo = BigScreenTradeAggInfoMapping.toTradeStockCountInfoModel(bigScreenTradeAggInfo);
                results.put(entry.getKey().toString(), tradeStockCountInfo);
            }
        }

        return results;
    }

    /**
     * 获取当前最新交易用户数走势数据
     *
     * @return
     */
    @Log4FYStock(value = "获取当前最新交易用户数走势数据")
    @Override
    public Map<String, TradeUserCountInfo> getTradeUserCountInfo() {
        //从Flink维护的缓存中获取
        Map<Object, Object> bigScreenTradeAggValues = redisCache.hmget(String.format(RedisKey.BIGSCREEN_ADS_TRADE_AGG, DateTimeUtil.getCurrentDateYYYYMMDD()));
        if (bigScreenTradeAggValues.isEmpty()) {
            return null;
        }
        Map<String, TradeUserCountInfo> results = new HashMap<>();
        for (Map.Entry<Object, Object> entry : bigScreenTradeAggValues.entrySet()) {
            log.info("-->当前最新交易用户数走势数据: 键 = " + entry.getKey() + ", 值 = " + entry.getValue());
            BigScreenTradeAggInfo bigScreenTradeAggInfo = objectMapper.convertValue(entry.getValue(), BigScreenTradeAggInfo.class);
            if (bigScreenTradeAggInfo != null) {
                TradeUserCountInfo tradeUserCountInfo = BigScreenTradeAggInfoMapping.toTradeUserCountInfoModel(bigScreenTradeAggInfo);
                results.put(entry.getKey().toString(), tradeUserCountInfo);
            }
        }

        return results;
    }

    /**
     * 获取指定交易日买入、卖出笔数走势数据
     *
     * @param tradeDate
     * @return
     */
    @Log4FYStock(value = "获取指定交易日买入、卖出笔数走势数据")
    @Override
    public Map<String, TradeStockCountInfo> getTradeStockCountInfo(String tradeDate) {
        Map<Object, Object> bigScreenTradeAggValues = redisCache.hmget(String.format(RedisKey.BIGSCREEN_ADS_TRADE_AGG, tradeDate.replace("-", "")));
        Map<String, TradeStockCountInfo> results = new HashMap<>();
        //1. 从Flink维护的缓存中读取
        if (!bigScreenTradeAggValues.isEmpty()) {
            for (Map.Entry<Object, Object> entry : bigScreenTradeAggValues.entrySet()) {
                log.info("-->交易日：{} 买入、卖出笔数走势数据: 键 = {} , 值 = {}", tradeDate, entry.getKey(), entry.getValue());
                BigScreenTradeAggInfo bigScreenTradeAggInfo = objectMapper.convertValue(entry.getValue(), BigScreenTradeAggInfo.class);
                if (bigScreenTradeAggInfo != null) {
                    TradeStockCountInfo tradeStockCountInfo = BigScreenTradeAggInfoMapping.toTradeStockCountInfoModel(bigScreenTradeAggInfo);
                    results.put(entry.getKey().toString(), tradeStockCountInfo);
                }
            }

            return results;
        }

        //2. 如果Flink维护的缓存中没有, 从本地维护的缓存中读取
        Map<Object, Object> tradeStockCountInfoObjectMaps = redisCache.hmget(String.format(RedisKey.BIGSCREEN_ADS_TRADE_AGG_TRADE_COUNT,
                tradeDate.replace("-", "")));
        if (!tradeStockCountInfoObjectMaps.isEmpty()) {
            for (Map.Entry<Object, Object> entry : tradeStockCountInfoObjectMaps.entrySet()) {
                log.info("-->交易日：{} 交易用户数走势数据: 键 = {}, 值 = {}", tradeDate, entry.getKey(), entry.getValue());
                TradeStockCountInfo tradeStockCountInfo = objectMapper.convertValue(entry.getValue(), TradeStockCountInfo.class);
                if (tradeStockCountInfo != null) {
                    results.put(entry.getKey().toString(), tradeStockCountInfo);
                }
            }

            return results;
        }

        //3. 如果Flink和本地维护的缓存中都没有, 则从数据库中读取统计
        Map<String, Object> tradeStockCountInfoCacheMaps = new HashMap<>();
        List<TradeStockCountInfo> tradeStockCountInfoList = tradeStockCountInfoMapper.getTradeStockCountInfo(tradeDate);

        tradeStockCountInfoList.forEach(o -> {
            tradeStockCountInfoCacheMaps.put(o.getTime(), o);
            results.put(o.getTime(), o);
        });

        //4. 将数据存入缓存,并设置15天过期
        redisCache.hmset(
                String.format(RedisKey.BIGSCREEN_ADS_TRADE_AGG_TRADE_COUNT,
                        tradeDate.replace("-", "")),
                tradeStockCountInfoCacheMaps,
                TimeUnit.DAYS,
                15);

        return results;
    }

    /**
     * 获取指定交易日交易额走势数据
     *
     * @param tradeDate
     * @return
     */
    @Log4FYStock(value = "获取指定交易日交易额走势数据")
    @Override
    public Map<String, TradeMoneySumInfo> getTradeMoneySumInfo(String tradeDate) {
        Map<Object, Object> bigScreenTradeAggValues = redisCache.hmget(String.format(RedisKey.BIGSCREEN_ADS_TRADE_AGG, tradeDate.replace("-", "")));
        Map<String, TradeMoneySumInfo> results = new HashMap<>();
        //1. 从Flink维护的缓存中读取
        if (!bigScreenTradeAggValues.isEmpty()) {
            for (Map.Entry<Object, Object> entry : bigScreenTradeAggValues.entrySet()) {
                log.info("-->交易日：{} 交易额走势数据: 键 = {}, 值 = {}", tradeDate, entry.getKey(), entry.getValue());
                BigScreenTradeAggInfo bigScreenTradeAggInfo = objectMapper.convertValue(entry.getValue(), BigScreenTradeAggInfo.class);
                if (bigScreenTradeAggInfo != null) {
                    TradeMoneySumInfo tradeMoneySumInfo = BigScreenTradeAggInfoMapping.toTradeMoneySumInfoModel(bigScreenTradeAggInfo);
                    results.put(entry.getKey().toString(), tradeMoneySumInfo);
                }
            }

            return results;
        }

        //2. 如果Flink维护的缓存中没有, 从本地维护的缓存中读取
        Map<Object, Object> tradeMoneySumInfoObjectMaps = redisCache.hmget(String.format(RedisKey.BIGSCREEN_ADS_TRADE_AGG_TRADE_SUM,
                tradeDate.replace("-", "")));
        if (!tradeMoneySumInfoObjectMaps.isEmpty()) {
            for (Map.Entry<Object, Object> entry : tradeMoneySumInfoObjectMaps.entrySet()) {
                log.info("-->交易日：{} 交易用户数走势数据: 键 = {}, 值 = {}", tradeDate, entry.getKey(), entry.getValue());
                TradeMoneySumInfo tradeMoneySumInfo = objectMapper.convertValue(entry.getValue(), TradeMoneySumInfo.class);
                if (tradeMoneySumInfo != null) {
                    results.put(entry.getKey().toString(), tradeMoneySumInfo);
                }
            }

            return results;
        }

        //3. 如果Flink和本地维护的缓存中都没有, 则从数据库中读取统计
        Map<String, Object> tradeMoneySumInfoCacheMaps = new HashMap<>();
        List<TradeMoneySumInfo> tradeMoneySumInfos = tradeMoneySumInfoMapper.getTradeMoneySumInfo(tradeDate);

        tradeMoneySumInfos.forEach(o -> {
            tradeMoneySumInfoCacheMaps.put(o.getTime(), o);
            results.put(o.getTime(), o);
        });

        //4. 将数据存入缓存,并设置15天过期
        redisCache.hmset(
                String.format(RedisKey.BIGSCREEN_ADS_TRADE_AGG_TRADE_SUM,
                        tradeDate.replace("-", "")),
                tradeMoneySumInfoCacheMaps,
                TimeUnit.DAYS,
                15);

        return results;
    }

    /**
     * 获取指定交易日交易用户数走势数据
     *
     * @return
     */
    @Log4FYStock(value = "获取指定交易日交易用户数走势数据")
    @Override
    public Map<String, TradeUserCountInfo> getTradeUserCountInfo(String tradeDate) {
        //1. 从Flink维护的缓存中读取
        Map<Object, Object> bigScreenTradeAggValues = redisCache.hmget(String.format(RedisKey.BIGSCREEN_ADS_TRADE_AGG, tradeDate.replace("-", "")));
        Map<String, TradeUserCountInfo> results = new HashMap<>();
        if (!bigScreenTradeAggValues.isEmpty()) {
            for (Map.Entry<Object, Object> entry : bigScreenTradeAggValues.entrySet()) {
                log.info("-->交易日：{} 交易用户数走势数据: 键 = {}, 值 = {}", tradeDate, entry.getKey(), entry.getValue());
                BigScreenTradeAggInfo bigScreenTradeAggInfo = objectMapper.convertValue(entry.getValue(), BigScreenTradeAggInfo.class);
                if (bigScreenTradeAggInfo != null) {
                    TradeUserCountInfo tradeUserCountInfo = BigScreenTradeAggInfoMapping.toTradeUserCountInfoModel(bigScreenTradeAggInfo);
                    results.put(entry.getKey().toString(), tradeUserCountInfo);
                }
            }

            return results;
        }

        //2. 如果Flink维护的缓存中没有, 从本地维护的缓存中读取
        Map<Object, Object> tradeUserCountInfoObjectMaps = redisCache.hmget(String.format(RedisKey.BIGSCREEN_ADS_TRADE_AGG_TRADE_USER_COUNT,
                tradeDate.replace("-", "")));
        if (!tradeUserCountInfoObjectMaps.isEmpty()) {
            for (Map.Entry<Object, Object> entry : tradeUserCountInfoObjectMaps.entrySet()) {
                log.info("-->交易日：{} 交易用户数走势数据: 键 = {}, 值 = {}", tradeDate, entry.getKey(), entry.getValue());
                TradeUserCountInfo tradeUserCountInfo = objectMapper.convertValue(entry.getValue(), TradeUserCountInfo.class);
                if (tradeUserCountInfo != null) {
                    results.put(entry.getKey().toString(), tradeUserCountInfo);
                }
            }

            return results;
        }

        //3. 如果Flink和本地维护的缓存中都没有, 则从数据库中读取统计
        Map<String, Object> tradeUserCountInfoCacheMaps = new HashMap<>();
        List<TradeUserCountInfo> tradeUserCountInfos = tradeUserCountInfoMapper.getTradeUserCountInfo(tradeDate);

        tradeUserCountInfos.forEach(o -> {
            tradeUserCountInfoCacheMaps.put(o.getTime(), o);
            results.put(o.getTime(), o);
        });

        //4. 将数据存入缓存,并设置15天过期
        redisCache.hmset(
                String.format(RedisKey.BIGSCREEN_ADS_TRADE_AGG_TRADE_USER_COUNT,
                        tradeDate.replace("-", "")),
                tradeUserCountInfoCacheMaps,
                TimeUnit.DAYS,
                15);

        return results;
    }

    /**
     * 获取近一月交易日交易用户数量走势数据
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @return
     */
    @Log4FYStock(value = "获取近一月交易日交易用户数量")
    @Cacheable(value = "BIGSCREEN-TRADEAGG-INFO", key = "'getLastMonthTradeUserCountInfo:BEGIN' + #beginTradeDate+ ':END' + #endTradeDate")
    @Override
    public Map<String, TradeUserCountInfo> getLastMonthTradeUserCountInfo(String beginTradeDate, String endTradeDate) {
        List<TradeUserCountInfo> tradeUserCountInfos = tradeUserCountInfoMapper.getLastMonthTradeUserCountInfo(beginTradeDate, endTradeDate);
        log.info("-->数据记录条数：{}", tradeUserCountInfos.size());
        Map<String, TradeUserCountInfo> results = new HashMap<>();
        tradeUserCountInfos.forEach(o -> {
            o.setStartTime(beginTradeDate);
            o.setEndTime(endTradeDate);
            results.put(o.getDate(), o);
        });
        return results;
    }

    /**
     * 获取近一月交易日交易额走势数据
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @return
     */
    @Log4FYStock(value = "获取近一月交易日交易额走势数据")
    @Cacheable(value = "BIGSCREEN-TRADEAGG-INFO", key = "'getLastMonthTradeMoneySumInfo:BEGIN' + #beginTradeDate+ ':END' + #endTradeDate")
    @Override
    public Map<String, TradeMoneySumInfo> getLastMonthTradeMoneySumInfo(String beginTradeDate, String endTradeDate) {
        List<TradeMoneySumInfo> tradeMoneySumInfos = tradeMoneySumInfoMapper.getLastMonthTradeMoneySumInfo(beginTradeDate, endTradeDate);
        log.info("-->数据记录条数：{}", tradeMoneySumInfos.size());
        Map<String, TradeMoneySumInfo> results = new HashMap<>();
        tradeMoneySumInfos.forEach(o -> {
            o.setStartTime(beginTradeDate);
            o.setEndTime(endTradeDate);
            results.put(o.getDate(), o);
        });
        return results;
    }

    /**
     * 获取近一个月交易日买入、卖出笔数走势数据
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @return
     */
    @Log4FYStock(value = "获取近一个月交易日买入、卖出笔数走势数据")
    @Cacheable(value = "BIGSCREEN-TRADEAGG-INFO", key = "'getLastMonthTradeTradeStockCountInfo:BEGIN' + #beginTradeDate+ ':END' + #endTradeDate")
    @Override
    public Map<String, TradeStockCountInfo> getLastMonthTradeTradeStockCountInfo(String beginTradeDate, String endTradeDate) {
        List<TradeStockCountInfo> tradeStockCountInfos = tradeStockCountInfoMapper.getLastMonthTradeTradeStockCountInfo(beginTradeDate, endTradeDate);
        log.info("-->数据记录条数：{}", tradeStockCountInfos.size());
        Map<String, TradeStockCountInfo> results = new HashMap<>();
        tradeStockCountInfos.forEach(o -> {
            o.setStartTime(beginTradeDate);
            o.setEndTime(endTradeDate);
            results.put(o.getDate(), o);
        });
        return results;
    }
}

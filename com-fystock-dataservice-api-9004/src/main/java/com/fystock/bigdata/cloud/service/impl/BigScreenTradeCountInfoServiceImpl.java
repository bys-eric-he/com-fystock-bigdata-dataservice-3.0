package com.fystock.bigdata.cloud.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fystock.bigdata.cloud.annotation.Log4FYStock;
import com.fystock.bigdata.cloud.cache.RedisCache;
import com.fystock.bigdata.cloud.cache.RedisKey;
import com.fystock.bigdata.cloud.mapper.BigScreenTradeCountInfoMapper;
import com.fystock.bigdata.cloud.mapping.BigScreenTradeCountInfoMapping;
import com.fystock.bigdata.cloud.model.TradeCountInfo;
import com.fystock.bigdata.cloud.redis.BigScreenTradeCountInfo;
import com.fystock.bigdata.cloud.service.BigScreenTradeCountInfoService;
import com.fystock.bigdata.cloud.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 港股交易个股数量
 *
 * @author He.Yong
 * @since 2021-06-18 18:05:19
 */
@Slf4j
@Service
public class BigScreenTradeCountInfoServiceImpl implements BigScreenTradeCountInfoService {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BigScreenTradeCountInfoMapper bigScreenTradeCountInfoMapper;

    /**
     * 获取当前港股交易个股数量
     *
     * @return
     */
    @Log4FYStock(value = "获取当前港股交易个股数量")
    @Override
    public Map<String, TradeCountInfo> getTradeCountInfo() {
        //从Flink维护的缓存中获取
        Map<Object, Object> bigScreenTradeCountInfoValues = redisCache.hmget(String.format(RedisKey.BIGSCREEN_ADS_TRADE_STOCK_COUNT, DateTimeUtil.getCurrentDateYYYYMMDD()));
        if (bigScreenTradeCountInfoValues.isEmpty()) {
            return null;
        }
        Map<String, TradeCountInfo> tradeCountInfos = new HashMap<>();

        for (Map.Entry<Object, Object> entry : bigScreenTradeCountInfoValues.entrySet()) {
            log.info("-->当前最新港股交易个股数量: 键 = {}, 值 = {}", entry.getKey(), entry.getValue());
            BigScreenTradeCountInfo bigScreenTradeCountInfo = objectMapper.convertValue(entry.getValue(), BigScreenTradeCountInfo.class);
            if (bigScreenTradeCountInfo != null) {
                TradeCountInfo tradeCountInfo = BigScreenTradeCountInfoMapping.toModel(bigScreenTradeCountInfo);
                tradeCountInfos.put(entry.getKey().toString(), tradeCountInfo);
            }
        }

        return tradeCountInfos;
    }

    /**
     * 获取指定交易日交易个股数量
     *
     * @param tradeDate 交易日
     * @return
     */
    @Log4FYStock(value = "获取指定交易日交易个股数量")
    @Override
    public Map<String, TradeCountInfo> getTradeCountInfo(String tradeDate) {
        //1. 从Flink维护的缓存中获取
        Map<Object, Object> bigScreenTradeCountInfoValues = redisCache.hmget(String.format(RedisKey.BIGSCREEN_ADS_TRADE_STOCK_COUNT, tradeDate.replace("-", "")));
        Map<String, TradeCountInfo> tradeCountInfoMaps = new HashMap<>();
        if (!bigScreenTradeCountInfoValues.isEmpty()) {
            for (Map.Entry<Object, Object> entry : bigScreenTradeCountInfoValues.entrySet()) {
                log.info("-->交易日：{} 港股交易个股数量: 键 = {}, 值 = {}", tradeDate, entry.getKey(), entry.getValue());
                BigScreenTradeCountInfo bigScreenTradeCountInfo = objectMapper.convertValue(entry.getValue(), BigScreenTradeCountInfo.class);
                if (bigScreenTradeCountInfo != null) {
                    TradeCountInfo tradeCountInfo = BigScreenTradeCountInfoMapping.toModel(bigScreenTradeCountInfo);
                    tradeCountInfoMaps.put(entry.getKey().toString(), tradeCountInfo);
                }
            }
            return tradeCountInfoMaps;
        }

        //2. 从数据库中获取
        Map<String, Object> tradeCountInfoObjectMaps = new HashMap<>();
        List<TradeCountInfo> tradeCountInfos = bigScreenTradeCountInfoMapper.getTradeCountInfo(tradeDate);
        tradeCountInfos.forEach(o -> {
            tradeCountInfoObjectMaps.put(o.getTime(), o);
            tradeCountInfoMaps.put(o.getTime(), o);
        });

        //3. 将数据存入缓存,并设置15天过期
        redisCache.hmset(
                String.format(RedisKey.BIGSCREEN_ADS_TRADE_STOCK_COUNT,
                        tradeDate.replace("-", "")),
                tradeCountInfoObjectMaps,
                TimeUnit.DAYS,
                15);
        return tradeCountInfoMaps;
    }
}

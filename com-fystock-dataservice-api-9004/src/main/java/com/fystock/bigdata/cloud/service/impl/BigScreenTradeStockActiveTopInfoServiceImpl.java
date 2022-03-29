package com.fystock.bigdata.cloud.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fystock.bigdata.cloud.annotation.Log4FYStock;
import com.fystock.bigdata.cloud.cache.RedisCache;
import com.fystock.bigdata.cloud.cache.RedisKey;
import com.fystock.bigdata.cloud.mapper.BigScreenTradeStockActiveTopInfoMapper;
import com.fystock.bigdata.cloud.redis.BigScreenTradeStockActiveTopInfo;
import com.fystock.bigdata.cloud.service.BigScreenTradeStockActiveTopInfoService;
import com.fystock.bigdata.cloud.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 港股交易个股活跃TOP排行榜
 *
 * @author He.Yong
 * @since 2021-06-23 16:43:08
 */
@Slf4j
@Service
public class BigScreenTradeStockActiveTopInfoServiceImpl implements BigScreenTradeStockActiveTopInfoService {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BigScreenTradeStockActiveTopInfoMapper bigScreenTradeStockActiveTopInfoMapper;

    /**
     * 获取当前交易活跃个股Top10
     *
     * @return
     */
    @Log4FYStock(value = "获取当前交易活跃个股Top10")
    @Override
    public List<BigScreenTradeStockActiveTopInfo> getTradeStockActiveTopInfo() {
        List<BigScreenTradeStockActiveTopInfo> tradeStockActiveTopInfos = new ArrayList<>();
        List<Object> objects = redisCache.lGet(String.format(RedisKey.BIGSCREEN_ADS_TRADE_STOCK_ACTIVE_TOP,
                DateTimeUtil.getCurrentDateYYYYMMDD()), 0, -1);
        if (ObjectUtils.isNotEmpty(objects)) {
            for (Object object : objects) {
                log.info("-->当前交易活跃个股Top10: " + object);
                BigScreenTradeStockActiveTopInfo bigScreenTradeStockActiveTopInfo = objectMapper.convertValue(object, BigScreenTradeStockActiveTopInfo.class);
                if (bigScreenTradeStockActiveTopInfo != null) {
                    tradeStockActiveTopInfos.add(bigScreenTradeStockActiveTopInfo);
                }
            }
        }
        return tradeStockActiveTopInfos;
    }

    /**
     * 根据指定交易日获取交易活跃个股Top10
     *
     * @param tradeDate
     * @return
     */
    @Log4FYStock(value = "根据指定交易日获取交易活跃个股Top10")
    @Override
    public List<BigScreenTradeStockActiveTopInfo> getTradeStockActiveTopInfo(String tradeDate) {
        List<BigScreenTradeStockActiveTopInfo> tradeStockActiveTopInfos = new ArrayList<>();
        //1. 从Flink维护的缓存中获取
        List<Object> objects = redisCache.lGet(String.format(RedisKey.BIGSCREEN_ADS_TRADE_STOCK_ACTIVE_TOP,
                tradeDate.replace("-", "")), 0, -1);
        if (ObjectUtils.isNotEmpty(objects)) {
            for (Object object : objects) {
                log.info("-->交易日: {} 交易活跃个股Top10: " + object, tradeDate);
                BigScreenTradeStockActiveTopInfo bigScreenTradeStockActiveTopInfo = objectMapper.convertValue(object, BigScreenTradeStockActiveTopInfo.class);
                if (bigScreenTradeStockActiveTopInfo != null) {
                    tradeStockActiveTopInfos.add(bigScreenTradeStockActiveTopInfo);
                }
            }
            return tradeStockActiveTopInfos;
        }
        //2. 从数据库中获取
        tradeStockActiveTopInfos = bigScreenTradeStockActiveTopInfoMapper.getTradeStockActiveTopInfo(tradeDate, 10);
        List<Object> cacheObjects = new ArrayList<>();

        tradeStockActiveTopInfos.forEach(o -> {
            o.setDate(tradeDate);
            cacheObjects.add(o);
        });

        //3. 将数据存入缓存,并设置15天过期
        redisCache.lSet(
                String.format(RedisKey.BIGSCREEN_ADS_TRADE_STOCK_ACTIVE_TOP,
                        tradeDate.replace("-", "")),
                cacheObjects,
                TimeUnit.DAYS,
                15);

        return tradeStockActiveTopInfos;
    }

    /**
     * 获取指定交易日区间获取交易活跃个股Top10
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @return
     */
    @Log4FYStock(value = "获取指定交易日区间获取交易活跃个股Top10")
    @Cacheable(value = "BIGSCREEN-TRADESTOCKACTIVETOP-INFO", key = "'getTradeStockActiveTopInfoByInterval:BEGIN' + #beginTradeDate+ ':END' + #endTradeDate")
    @Override
    public List<BigScreenTradeStockActiveTopInfo> getTradeStockActiveTopInfoByInterval(String beginTradeDate, String endTradeDate) {
        List<BigScreenTradeStockActiveTopInfo> tradeStockActiveTopInfos = bigScreenTradeStockActiveTopInfoMapper.getTradeStockActiveTopInfoByInterval(beginTradeDate, endTradeDate, 10);

        log.info("-->数据记录条数：{}", tradeStockActiveTopInfos.size());

        tradeStockActiveTopInfos.forEach(o->{
            o.setStartTime(beginTradeDate);
            o.setEndTime(endTradeDate);
        });
        return tradeStockActiveTopInfos;
    }
}

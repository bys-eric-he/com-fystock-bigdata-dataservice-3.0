package com.fystock.bigdata.cloud.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fystock.bigdata.cloud.annotation.Log4FYStock;
import com.fystock.bigdata.cloud.cache.RedisCache;
import com.fystock.bigdata.cloud.cache.RedisKey;
import com.fystock.bigdata.cloud.mapper.BigScreenTradeInduInfoMapper;
import com.fystock.bigdata.cloud.mapping.BigScreenTradeInduInfoMapping;
import com.fystock.bigdata.cloud.model.TradeInduInfo;
import com.fystock.bigdata.cloud.redis.BigScreenTradeInduInfo;
import com.fystock.bigdata.cloud.service.BigScreenTradeInduInfoService;
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
 * 港股行业交易量排行榜
 *
 * @author He.Yong
 * @since 2021-06-17 18:20:19
 */
@Slf4j
@Service
public class BigScreenTradeInduInfoServiceImpl implements BigScreenTradeInduInfoService {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BigScreenTradeInduInfoMapper bigScreenTradeInduInfoMapper;

    /**
     * 获取港股行业交易量TOP10
     *
     * @return
     */
    @Log4FYStock(value = "获取港股行业交易量TOP10")
    @Override
    public List<TradeInduInfo> getTradeInduTopInfo() {
        List<TradeInduInfo> tradeInduInfos = new ArrayList<>();
        //从Flink维护的缓存中获取
        List<Object> objects = redisCache.lGet(String.format(RedisKey.BIGSCREEN_ADS_TRADE_INDU_TOP,
                DateTimeUtil.getCurrentDateYYYYMMDD()), 0, -1);
        if (ObjectUtils.isNotEmpty(objects)) {
            for (Object object : objects) {
                log.info("-->当前最新港股行业交易量Top10: " + object);
                BigScreenTradeInduInfo bigScreenTradeInduInfo = objectMapper.convertValue(object, BigScreenTradeInduInfo.class);
                if (bigScreenTradeInduInfo != null) {
                    TradeInduInfo tradeInduInfo = BigScreenTradeInduInfoMapping.toModel(bigScreenTradeInduInfo);
                    tradeInduInfos.add(tradeInduInfo);
                }
            }
        }

        return tradeInduInfos;
    }

    /**
     * 获取指定交易日港股行业交易量Top10
     *
     * @param tradeDate
     * @return
     */
    @Log4FYStock(value = "获取指定交易日港股行业交易量Top10")
    @Override
    public List<TradeInduInfo> getTradeInduTopInfo(String tradeDate) {
        List<TradeInduInfo> tradeInduInfos = new ArrayList<>();
        //1. 从Flink维护的缓存中获取
        List<Object> objects = redisCache.lGet(String.format(RedisKey.BIGSCREEN_ADS_TRADE_INDU_TOP,
                tradeDate.replace("-", "")), 0, -1);

        if (ObjectUtils.isNotEmpty(objects)) {
            for (Object object : objects) {
                log.info("-->交易日: {} 港股行业交易量Top10: " + object, tradeDate);
                BigScreenTradeInduInfo bigScreenTradeInduInfo = objectMapper.convertValue(object, BigScreenTradeInduInfo.class);
                if (bigScreenTradeInduInfo != null) {
                    TradeInduInfo tradeInduInfo = BigScreenTradeInduInfoMapping.toModel(bigScreenTradeInduInfo);
                    tradeInduInfos.add(tradeInduInfo);
                }
            }

            return tradeInduInfos;
        }

        //2. 从数据库中获取
        tradeInduInfos = bigScreenTradeInduInfoMapper.getTradeInduTopInfo(tradeDate, 10);
        //设置交易日
        tradeInduInfos.forEach(o -> o.setDate(tradeDate));

        List<Object> tradeInduInfoObjects = new ArrayList<>(tradeInduInfos);

        //3. 将数据存入缓存,并设置15天过期
        redisCache.lSet(
                String.format(RedisKey.BIGSCREEN_ADS_TRADE_INDU_TOP,
                        tradeDate.replace("-", "")),
                tradeInduInfoObjects,
                TimeUnit.DAYS,
                15);

        return tradeInduInfos;
    }

    /**
     * 获取指定交易日区间港股行业交易量Top10
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @return
     */
    @Log4FYStock(value = "获取指定交易日区间港股行业交易量Top10")
    @Cacheable(value = "BIGSCREEN-TRADEINDU-INFO", key = "'getTradeInduTopInfoByInterval:BEGIN' + #beginTradeDate+ ':END' + #endTradeDate")
    @Override
    public List<TradeInduInfo> getTradeInduTopInfoByInterval(String beginTradeDate, String endTradeDate) {
        List<TradeInduInfo> tradeInduInfos = bigScreenTradeInduInfoMapper.getTradeInduTopInfoByInterval(beginTradeDate, endTradeDate, 10);
        //设置交易日
        tradeInduInfos.forEach(o -> {
            o.setStartTime(beginTradeDate);
            o.setEndTime(endTradeDate);
        });
        log.info("-->数据记录条数：{}", tradeInduInfos.size());
        return tradeInduInfos;
    }
}

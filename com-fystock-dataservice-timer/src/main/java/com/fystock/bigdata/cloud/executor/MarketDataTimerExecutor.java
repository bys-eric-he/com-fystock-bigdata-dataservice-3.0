package com.fystock.bigdata.cloud.executor;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fystock.bigdata.cloud.cache.RedisCache;
import com.fystock.bigdata.cloud.cache.RedisKey;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.IRealTimeMarketCalculationRPCProviderService;
import com.fystock.bigdata.cloud.service.IStockTradeCalendarRPCProviderService;
import com.fystock.bigdata.cloud.utils.DateTimeUtil;
import com.xxl.job.core.handler.IJobHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * 定时将上一个交易日的行情Top10历史数据同步到缓存
 *
 * @author He.Yong
 * @since 2021-06-03 11:24:25
 */
@Slf4j
@Component("MarketDataTimerExecutor")
public class MarketDataTimerExecutor extends IJobHandler {

    @Autowired
    private RedisCache redisCache;

    /**
     * Dubbo远程服务调用
     */
    @DubboReference
    private IStockTradeCalendarRPCProviderService iStockTradeCalendarRPCProviderService;

    /**
     * Dubbo远程服务调用
     */
    @DubboReference
    private IRealTimeMarketCalculationRPCProviderService iRealTimeMarketCalculationRPCProviderService;

    @Override
    public void execute() throws Exception {
        try {
            executeTimer();
        } catch (Exception exception) {
            log.error("-----执行定时同步上一个交易日的行情Top10历史数据到缓存发生异常! 异常信息: ", exception);
            throw exception;
        }
    }

    /**
     * 定时将前一个交易日的数据打入缓存
     */
    public void executeTimer() {
        String tradeDate = DateTimeUtil.getYesterdayDate();
        log.info("-----开始执行定时器任务,将交易日：{} 数据写入缓存!-------", tradeDate);
        if (this.isTradeDay(tradeDate)) {
            Object data = this.getMarketInfo(tradeDate);
            this.saveToCache(String.format(RedisKey.REALTIME_MARKET_CALCULATION_TRADEDATE, tradeDate), data);
        } else {
            log.info("-----日期：{} 不是交易日!-------", tradeDate);
        }
        log.info("-----定时器任务,将交易日：{} 数据写入缓存结束!-------", tradeDate);
    }

    /**
     * 判断当前年月日是否为交易日
     *
     * @param tradeDate
     * @return
     */
    private boolean isTradeDay(String tradeDate) {
        log.info("-----开始判断日期：{} 是否为交易日-------", tradeDate);
        try {

            LocalDate localDate = DateTimeUtil.getLocalDateByString(tradeDate);
            CommonResult<Object> response = iStockTradeCalendarRPCProviderService.findByNormalDate(localDate, "HK");

            if (ObjectUtils.isNotEmpty(response)) {
                if (response.getCode().equals(200)) {
                    JSONObject data = JSONObject.parseObject(JSONObject.toJSONString(response.getData()));
                    log.info("-----获取交易日历数据成功-------");
                    ObjectMapper objectMapper = new ObjectMapper();
                    log.info("-----数据包：" + objectMapper.writeValueAsString(data));
                    return data.get("isTradeDay").toString().equals("1");
                } else {
                    log.warn("******查询交易日: {} 日历数据失败! 失败原因：{}******", tradeDate, response);
                    return false;
                }
            } else {
                log.warn("******-定时任务接口调用, 查询交易日: {} 日历数据响应返回为空!******", tradeDate);
                return false;
            }
        } catch (JsonProcessingException exception) {
            log.error("**********获取交易日历数据发生异常!***********" + exception);
            return false;
        }
    }

    /**
     * 获取前一个交易日的历史数据
     *
     * @param tradeDate
     * @return
     */
    private Object getMarketInfo(String tradeDate) {
        log.info("-----开始获取交易日: {} 历史数据-------", tradeDate);
        try {
            CommonResult<Object> response = iRealTimeMarketCalculationRPCProviderService.getMarketDataByTradeDateForTimer(tradeDate);

            if (ObjectUtils.isNotEmpty(response)) {

                if (response.getCode().equals(200)) {
                    Object data = response.getData();
                    log.info("-----获取交易日: {} 数据成功-------", tradeDate);
                    return data;
                } else {
                    log.warn("******查询交易日: {} 数据失败! 失败原因：{}******", tradeDate, response);
                }
            } else {
                log.warn("-----定时任务接口调用,查询交易日：{} 响应返回为空!-------", tradeDate);
            }
        } catch (Exception exception) {
            log.error("**********获取交易日: {} 的历史数据发生异常! 详细信息：{}", tradeDate, exception);
        }

        return null;
    }

    /**
     * 将数据存入缓存中
     *
     * @param key
     * @param value
     */
    private void saveToCache(String key, Object value) {
        /*redisCache.put(key, result);*/
        boolean result = redisCache.set(key, value);
        if (result) {
            log.info("-----数据已成功写入缓存, 键 :{} -----", key);
        } else {
            log.error("******数据写入环存失败! 键 :{} ******", key);
        }
    }
}
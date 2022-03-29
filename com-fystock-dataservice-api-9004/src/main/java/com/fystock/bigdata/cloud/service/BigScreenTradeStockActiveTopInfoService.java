package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.redis.BigScreenTradeStockActiveTopInfo;

import java.util.List;

/**
 * 港股交易个股活跃TOP排行榜
 *
 * @author He.Yong
 * @since 2021-06-23 16:43:08
 */
public interface BigScreenTradeStockActiveTopInfoService {
    /**
     * 获取当前交易活跃个股Top10
     *
     * @return
     */
    List<BigScreenTradeStockActiveTopInfo> getTradeStockActiveTopInfo();

    /**
     * 根据指定交易日获取交易活跃个股Top10
     *
     * @param tradeDate
     * @return
     */
    List<BigScreenTradeStockActiveTopInfo> getTradeStockActiveTopInfo(String tradeDate);

    /**
     * 根据指定交易日区间获取交易活跃个股Top10
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @return
     */
    List<BigScreenTradeStockActiveTopInfo> getTradeStockActiveTopInfoByInterval(String beginTradeDate, String endTradeDate);
}

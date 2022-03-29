package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.model.TradeMoneySumInfo;
import com.fystock.bigdata.cloud.model.TradeStockCountInfo;
import com.fystock.bigdata.cloud.model.TradeUserCountInfo;

import java.util.Map;

/**
 * 交易用户数走势、成交笔数走势、交易额走势实时数据
 *
 * @author He.Yong
 * @since 2021-06-17 18:20:19
 */
public interface BigScreenTradeAggInfoService {
    /**
     * 获取当前最新交易额走势数据
     *
     * @return
     */
    Map<String, TradeMoneySumInfo> getTradeMoneySumInfo();

    /**
     * 获取当前最新买入、卖出笔数走势数据
     *
     * @return
     */
    Map<String, TradeStockCountInfo> getTradeStockCountInfo();

    /**
     * 获取当前最新交易用户数走势数据
     *
     * @return
     */
    Map<String, TradeUserCountInfo> getTradeUserCountInfo();

    /**
     * 获取指定交易日交易额走势数据
     *
     * @param tradeDate
     * @return
     */
    Map<String, TradeMoneySumInfo> getTradeMoneySumInfo(String tradeDate);


    /**
     * 获取指定交易日买入、卖出笔数走势数据
     *
     * @param tradeDate
     * @return
     */
    Map<String, TradeStockCountInfo> getTradeStockCountInfo(String tradeDate);

    /**
     * 获取指定交易日交易用户数走势数据
     *
     * @param tradeDate
     * @return
     */
    Map<String, TradeUserCountInfo> getTradeUserCountInfo(String tradeDate);

    /**
     * 获取近一月交易日交易用户数量走势数据
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @return
     */
    Map<String, TradeUserCountInfo> getLastMonthTradeUserCountInfo(String beginTradeDate, String endTradeDate);

    /**
     * 获取近一月交易日交易额走势数据
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @return
     */
    Map<String, TradeMoneySumInfo> getLastMonthTradeMoneySumInfo(String beginTradeDate, String endTradeDate);

    /**
     * 获取近一个月交易日买入、卖出笔数走势数据
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @return
     */
    Map<String, TradeStockCountInfo> getLastMonthTradeTradeStockCountInfo(String beginTradeDate, String endTradeDate);
}

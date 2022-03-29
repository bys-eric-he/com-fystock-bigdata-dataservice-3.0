package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.model.TradeMoneySumInfo;
import com.fystock.bigdata.cloud.model.TradeStockCountInfo;
import com.fystock.bigdata.cloud.model.TradeUserCountInfo;
import com.fystock.bigdata.cloud.redis.BigScreenTradeAggInfo;

/**
 * 数据模型层转换
 *
 * @author He.Yong
 * @since 2021-06-17 18:15:19
 */
public class BigScreenTradeAggInfoMapping {
    /**
     * 转换交易金额走势
     *
     * @param bigScreenTradeAggInfo
     * @return
     */
    public static TradeMoneySumInfo toTradeMoneySumInfoModel(BigScreenTradeAggInfo bigScreenTradeAggInfo) {
        if (bigScreenTradeAggInfo == null) {
            return null;
        }

        TradeMoneySumInfo tradeMoneySumInfo = new TradeMoneySumInfo();
        tradeMoneySumInfo.setDate(bigScreenTradeAggInfo.getDate());
        tradeMoneySumInfo.setStartTime(bigScreenTradeAggInfo.getStartTime());
        tradeMoneySumInfo.setEndTime(bigScreenTradeAggInfo.getEndTime());
        tradeMoneySumInfo.setTradeSum(bigScreenTradeAggInfo.getTradeSum());
        tradeMoneySumInfo.setVersion(bigScreenTradeAggInfo.getVersion());
        tradeMoneySumInfo.setTime(bigScreenTradeAggInfo.getTime());

        return tradeMoneySumInfo;
    }

    /**
     * 交易个股买入、卖出成交笔数走势
     *
     * @param bigScreenTradeAggInfo
     * @return
     */
    public static TradeStockCountInfo toTradeStockCountInfoModel(BigScreenTradeAggInfo bigScreenTradeAggInfo) {
        if (bigScreenTradeAggInfo == null) {
            return null;
        }

        TradeStockCountInfo tradeStockCountInfo = new TradeStockCountInfo();

        tradeStockCountInfo.setDate(bigScreenTradeAggInfo.getDate());
        tradeStockCountInfo.setStartTime(bigScreenTradeAggInfo.getStartTime());
        tradeStockCountInfo.setEndTime(bigScreenTradeAggInfo.getEndTime());
        tradeStockCountInfo.setTradeBuyCount(bigScreenTradeAggInfo.getTradeBuyCount());
        tradeStockCountInfo.setTradeSaleCount(bigScreenTradeAggInfo.getTradeSaleCount());
        tradeStockCountInfo.setVersion(bigScreenTradeAggInfo.getVersion());
        tradeStockCountInfo.setTime(bigScreenTradeAggInfo.getTime());

        return tradeStockCountInfo;
    }

    /**
     * 转换成交易用户数量走势
     *
     * @param bigScreenTradeAggInfo
     * @return
     */
    public static TradeUserCountInfo toTradeUserCountInfoModel(BigScreenTradeAggInfo bigScreenTradeAggInfo) {
        if (bigScreenTradeAggInfo == null) {
            return null;
        }

        TradeUserCountInfo tradeUserCountInfo = new TradeUserCountInfo();
        tradeUserCountInfo.setDate(bigScreenTradeAggInfo.getDate());
        tradeUserCountInfo.setStartTime(bigScreenTradeAggInfo.getStartTime());
        tradeUserCountInfo.setEndTime(bigScreenTradeAggInfo.getEndTime());
        tradeUserCountInfo.setTradeUserCount(bigScreenTradeAggInfo.getTradeUserCount());
        tradeUserCountInfo.setVersion(bigScreenTradeAggInfo.getVersion());
        tradeUserCountInfo.setTime(bigScreenTradeAggInfo.getTime());

        return tradeUserCountInfo;
    }
}

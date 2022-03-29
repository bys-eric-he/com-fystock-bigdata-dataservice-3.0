package com.fystock.bigdata.cloud.cache;

/**
 * Redis缓存Key枚举
 *
 * @author He.Yong
 * @since 2021-06-16 15:22:05
 */
public interface RedisKey {

    /**
     * 上一个交易日的行情Top10历史数据
     */
    String REALTIME_MARKET_CALCULATION_TRADEDATE = "REALTIME-MARKET-CALCULATION::getMarketDataByTradeDate:%s";

    /**
     * 当日交易日的行情Top10数据
     */
    String REALTIME_MARKET_CALCULATION_LATEST = "REALTIME-MARKET-CALCULATION::getMarketDataByTradeDate:latest:%s";

    /**
     * 交易用户数走势、成交笔数走势、交易额走势
     */
    String BIGSCREEN_ADS_TRADE_AGG = "bigscreen:ads_trade_agg:%s";

    /**
     * 交易成交买入、卖出笔数走势
     */
    String BIGSCREEN_ADS_TRADE_AGG_TRADE_COUNT = "bigscreen:ads_trade_agg_trade_count:%s";

    /**
     * 交易成交额走势
     */
    String BIGSCREEN_ADS_TRADE_AGG_TRADE_SUM = "bigscreen:ads_trade_agg_trade_sum:%s";

    /**
     * 交易用户数走势
     */
    String BIGSCREEN_ADS_TRADE_AGG_TRADE_USER_COUNT = "bigscreen:ads_trade_agg_trade_user_count:%s";

    /**
     * 股票交易个股数量
     */
    String BIGSCREEN_ADS_TRADE_STOCK_COUNT = "bigscreen:ads_trade_stcok_count:%s";

    /**
     * 股票交易行业排行榜
     */
    String BIGSCREEN_ADS_TRADE_INDU_TOP = "bigscreen:ads_trade_indu_top:%s";

    /**
     * 股票个股交易排行榜
     */
    String BIGSCREEN_ADS_TRADE_STOCK_ACTIVE_TOP = "bigscreen:ads_trade_stock_active_top:%s";
}

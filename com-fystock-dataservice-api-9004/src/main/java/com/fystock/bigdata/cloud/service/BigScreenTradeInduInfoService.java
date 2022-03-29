package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.model.TradeInduInfo;

import java.util.List;

/**
 * 港股行业交易量排行榜
 *
 * @author He.Yong
 * @since 2021-06-18 15:49:19
 */
public interface BigScreenTradeInduInfoService {
    /**
     * 获取当前港股行业交易量TOP10
     *
     * @return
     */
    List<TradeInduInfo> getTradeInduTopInfo();

    /**
     * 获取指定交易日港股行业交易量Top10
     *
     * @param tradeDate
     * @return
     */
    List<TradeInduInfo> getTradeInduTopInfo(String tradeDate);

    /**
     * 获取指定交易日区间港股行业交易量Top10
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @return
     */
    List<TradeInduInfo> getTradeInduTopInfoByInterval(String beginTradeDate, String endTradeDate);
}

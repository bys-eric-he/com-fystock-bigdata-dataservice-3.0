package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.model.TradeCountInfo;

import java.util.Map;

/**
 * 港股交易个股数量
 *
 * @author He.Yong
 * @since 2021-06-18 18:05:19
 */
public interface BigScreenTradeCountInfoService {
    /**
     * 获取当前港股交易个股数量
     *
     * @return
     */
    Map<String, TradeCountInfo> getTradeCountInfo();

    /**
     * 获取指定交易日交易个股数量
     *
     * @return
     */
    Map<String, TradeCountInfo> getTradeCountInfo(String tradeDate);
}

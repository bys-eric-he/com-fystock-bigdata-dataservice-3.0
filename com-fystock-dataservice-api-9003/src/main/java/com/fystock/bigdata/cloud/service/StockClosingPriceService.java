package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.model.StockClosingPriceModel;
import com.fystock.bigdata.cloud.model.StockRelativeAbsoluteIndexModel;

/**
 * 股票收盘价
 *
 * @author He.Yong
 * @since 2021-08-06 11:15:22
 */
public interface StockClosingPriceService {

    /**
     * 获取指定股票指定交易日收盘价
     *
     * @param stockCode
     * @param tradeDate
     * @return
     */
    StockClosingPriceModel getStockClosingPriceInfo(String stockCode, String tradeDate);

    /**
     * 获取指定两只股票同一交易日相对强势指数
     *
     * @param stockCodeA
     * @param stockCodeB
     * @param tradeDate
     * @return
     */
    StockRelativeAbsoluteIndexModel getStockRelativeAbsoluteIndex(String stockCodeA, String stockCodeB, String tradeDate);
}

package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.entity.clickhouse.SellShortTransactionDataInfo;
import com.fystock.bigdata.cloud.model.SellShortCostInfo;
import com.fystock.bigdata.cloud.model.SellShortRatioInfo;

import java.util.List;

/**
 * 即日卖空成交数据
 *
 * @author He.Yong
 * @since 2021-08-04 17:48:59
 */
public interface SellShortTransactionDataInfoService {

    /**
     * 持久化即日卖空成交数据
     *
     * @param sellShortTransactionDataInfos
     */
    int insertSellShortTransactionData(List<SellShortTransactionDataInfo> sellShortTransactionDataInfos);

    /**
     * 获取指定交易日卖空成交数据
     *
     * @param tradeDate
     * @return
     */
    List<SellShortTransactionDataInfo> getSellShortTransactionData(String tradeDate);

    /**
     * 获取指定交易日、指定股票卖空成本
     *
     * @param tradeDate
     * @param stockCode
     * @return
     */
    SellShortCostInfo getSellShortCostInfo(String tradeDate, String stockCode);

    /**
     * 获取指定交易日、指定股票做空占比
     *
     * @param tradeDate
     * @param stockCode
     * @return
     */
    SellShortRatioInfo getSellShortRatioInfo(String tradeDate, String stockCode);
}

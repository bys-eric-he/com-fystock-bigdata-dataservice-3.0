package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.model.OrderClassOfProportionInfo;
import com.fystock.bigdata.cloud.model.TickerQuatInfo;

import java.util.List;

/**
 * N日增仓占比
 */
public interface OrderClassOfProportionInfoService {

    /**
     * 计算指定股票N日内的增仓占比
     *
     * @param stockCode
     * @param startTradeDate
     * @param endTradeDate
     * @return
     */
    List<TickerQuatInfo> getTickerQuatInfo(String stockCode, String startTradeDate, String endTradeDate);

    /**
     * 计算指定股票N日内的增仓占比
     *
     * @param stockCode
     * @param startTradeDate
     * @param endTradeDate
     * @return
     */
    OrderClassOfProportionInfo getOrderClassOfProportionInfo(String stockCode, String startTradeDate, String endTradeDate);

}

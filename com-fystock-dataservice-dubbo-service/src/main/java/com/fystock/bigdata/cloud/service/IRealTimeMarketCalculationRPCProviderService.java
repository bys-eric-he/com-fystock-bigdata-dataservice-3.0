package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.response.CommonResult;

/**
 * 实时行情计算结果 Dubbo对内提供的RPC接口
 *
 * @author He.Yong
 * @since 2021-09-09 10:39:40
 */
public interface IRealTimeMarketCalculationRPCProviderService {
    /**
     * 按指定交易日获取所有版块中每只股票最后一次计算结果
     *
     * @param tradeDate
     * @return
     */
    CommonResult<Object> getMarketDataByTradeDateForTimer(String tradeDate);
}

package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.response.CommonResult;

import java.time.LocalDate;

/**
 * 交易日历表 Dubbo对内提供的RPC接口
 *
 * @author He.Yong
 * @since 2021-09-09 10:13:24
 */
public interface IStockTradeCalendarRPCProviderService {
    /**
     * 根据自然日期、区域编码,获取交易日
     *
     * @param normalDate
     * @param regionCode
     * @return
     */
    CommonResult<Object> findByNormalDate(LocalDate normalDate, String regionCode);
}

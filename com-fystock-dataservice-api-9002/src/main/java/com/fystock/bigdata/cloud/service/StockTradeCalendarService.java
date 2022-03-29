package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.model.StockTradeCalendarModel;

import java.time.LocalDate;
import java.util.List;

/**
 * 交易日历查询
 *
 * @author He.Yong
 * @since 2021-04-21 17:17:08
 */
public interface StockTradeCalendarService {
    /**
     * 根据自然日期 及区域编码获取交易日
     *
     * @param normalDate
     * @param regionCode
     * @return
     */
    StockTradeCalendarModel findByNormalDateAndRegionCode(LocalDate normalDate, String regionCode);

    /**
     * 根据年-月获取当月的交易日历
     *
     * @param normalDate
     * @param regionCode
     * @return
     */
   List<StockTradeCalendarModel> findByYearAndMonth(LocalDate normalDate, String regionCode);
}

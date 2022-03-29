package com.fystock.bigdata.cloud.service.impl;

import com.fystock.bigdata.cloud.annotation.Log4FYStock;
import com.fystock.bigdata.cloud.mapper.StockTradeCalendarMapper;
import com.fystock.bigdata.cloud.entity.mysql.StockTradeCalendar;
import com.fystock.bigdata.cloud.mapping.StockTradeCalendarMapping;
import com.fystock.bigdata.cloud.model.StockTradeCalendarModel;
import com.fystock.bigdata.cloud.service.StockTradeCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 交易日历查询
 *
 * @author He.Yong
 * @since 2021-04-21 17:17:08
 */
@CacheConfig(cacheNames = "STOCK-TRADE-CALENDAR")
@Service
public class StockTradeCalendarServiceImpl implements StockTradeCalendarService {

    private StockTradeCalendarMapper stockTradeCalendarMapper;

    @Autowired
    public StockTradeCalendarServiceImpl(StockTradeCalendarMapper stockTradeCalendarMapper) {
        this.stockTradeCalendarMapper = stockTradeCalendarMapper;
    }

    /**
     * 根据自然日期 及区域编码获取交易日
     *
     * @param normalDate
     * @param regionCode
     * @return
     */
    @Log4FYStock(value = "根据自然日期 及区域编码获取交易日")
    //@Cacheable(/*value = "STOCK-TRADE-CALENDAR",*/ key = "'findByNormalDateAndRegionCode:' + #normalDate.year+ #normalDate.monthValue + #normalDate.dayOfMonth+ 'regionCode:' + #regionCode")
    @Override
    public StockTradeCalendarModel findByNormalDateAndRegionCode(LocalDate normalDate, String regionCode) {
        StockTradeCalendar stockTradeCalendar = stockTradeCalendarMapper.findByNormalDateAndRegionCode(normalDate, regionCode);
        return StockTradeCalendarMapping.toModel(stockTradeCalendar);
    }

    /**
     * 根据年-月获取当月的交易日历
     *
     * @param normalDate
     * @param regionCode
     * @return
     */
    @Log4FYStock(value = "根据年-月获取当月的交易日历")
    @Cacheable(/*value = "STOCK-TRADE-CALENDAR",*/ key = "'findByYearAndMonth:' + #normalDate.year+ #normalDate.monthValue + 'regionCode:' + #regionCode")
    @Override
    public List<StockTradeCalendarModel> findByYearAndMonth(LocalDate normalDate, String regionCode) {
        List<StockTradeCalendar> stockTradeCalendars = stockTradeCalendarMapper.findByYearAndMonth(normalDate.getYear(), normalDate.getMonth().getValue(), regionCode);

        List<StockTradeCalendarModel> results = new ArrayList<>();
        stockTradeCalendars.forEach(o -> results.add(StockTradeCalendarMapping.toModel(o)));

        return results;
    }
}

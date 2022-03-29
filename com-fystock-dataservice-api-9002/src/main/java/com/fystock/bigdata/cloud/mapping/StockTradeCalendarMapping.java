package com.fystock.bigdata.cloud.mapping;

import com.fystock.bigdata.cloud.entity.mysql.StockTradeCalendar;
import com.fystock.bigdata.cloud.model.StockTradeCalendarModel;

/**
 * 实体层和模型层之间数据转换
 *
 * @author He.Yong
 * @since 2021-02-04 16:36:22
 */
public class StockTradeCalendarMapping {
    /**
     * Entity 转换成 Model
     *
     * @param stockTradeCalendar
     * @return
     */
    public static StockTradeCalendarModel toModel(StockTradeCalendar stockTradeCalendar) {
        if (stockTradeCalendar == null) {
            return null;
        }

        StockTradeCalendarModel stockTradeCalendarModel = new StockTradeCalendarModel();
        stockTradeCalendarModel.setCalendarId(stockTradeCalendar.getCalendarId());
        stockTradeCalendarModel.setIsTradeDay(stockTradeCalendar.getIsTradeDay());
        stockTradeCalendarModel.setLastTrade(stockTradeCalendar.getLastTrade());
        stockTradeCalendarModel.setNextTrade(stockTradeCalendar.getNextTrade());
        stockTradeCalendarModel.setLastMonthTrade(stockTradeCalendar.getLastMonthTrade());
        stockTradeCalendarModel.setExternalTime(stockTradeCalendar.getExternalTime());
        stockTradeCalendarModel.setIsMonthEnd(stockTradeCalendar.getIsMonthEnd());
        stockTradeCalendarModel.setIsWeekEnd(stockTradeCalendar.getIsWeekEnd());
        stockTradeCalendarModel.setIsYearEnd(stockTradeCalendar.getIsYearEnd());
        stockTradeCalendarModel.setLastWeekTrade(stockTradeCalendar.getLastWeekTrade());
        stockTradeCalendarModel.setLastYearTrade(stockTradeCalendar.getLastYearTrade());
        stockTradeCalendarModel.setNormalDate(stockTradeCalendar.getNormalDate());
        stockTradeCalendarModel.setRegionCode(stockTradeCalendar.getRegionCode());
        stockTradeCalendarModel.setCreateTime(stockTradeCalendar.getCreateTime());
        stockTradeCalendarModel.setUpdateTime(stockTradeCalendar.getUpdateTime());
        stockTradeCalendarModel.setExternalTime(stockTradeCalendar.getExternalTime());
        stockTradeCalendarModel.setRemark(stockTradeCalendar.getRemark());

        return stockTradeCalendarModel;
    }
}

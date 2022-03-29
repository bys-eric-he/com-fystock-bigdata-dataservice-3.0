package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.StockTradeCalendar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * 交易日期Mapper接口
 *
 * @author He.Yong
 * @since 2021-02-04 16:21:22
 */
@Mapper
public interface StockTradeCalendarMapper {
    /**
     * 根据自然日期区域编码 获取交易日
     *
     * @param normalDate
     * @param regionCode
     * @return
     */
    StockTradeCalendar findByNormalDateAndRegionCode(@Param("normalDate") LocalDate normalDate,
                                                     @Param("regionCode") String regionCode);

    /**
     * 根据年-月获取当月的交易日历
     *
     * @param year
     * @param month
     * @param regionCode
     * @return
     */
    List<StockTradeCalendar> findByYearAndMonth(@Param("year") int year, @Param("month") int month,
                                                @Param("regionCode") String regionCode);
}

package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.model.TradeMoneySumInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 交易金额走势
 *
 * @author He.Yong
 * @since 2021-06-23 12:03:29
 */
@Mapper
public interface TradeMoneySumInfoMapper {
    /**
     * 获取指定交易日交易金额走势
     *
     * @param tradeDate
     * @return
     */
    List<TradeMoneySumInfo> getTradeMoneySumInfo(@Param("tradeDate") String tradeDate);

    /**
     * 获取近一月交易日交易额走势数据
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @return
     */
    List<TradeMoneySumInfo> getLastMonthTradeMoneySumInfo(@Param("beginTradeDate") String beginTradeDate, @Param("endTradeDate") String endTradeDate);
}

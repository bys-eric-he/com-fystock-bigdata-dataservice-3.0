package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.model.TradeUserCountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 港股交易用户数走势
 *
 * @author He.Yong
 * @since 2021-06-23 13:57:07
 */
@Mapper
public interface TradeUserCountInfoMapper {
    /**
     * 获取指定交易日交易用户数量
     *
     * @param tradeDate
     * @return
     */
    List<TradeUserCountInfo> getTradeUserCountInfo(@Param("tradeDate")String tradeDate);

    /**
     * 获取近一月交易用户数量
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @return
     */
    List<TradeUserCountInfo> getLastMonthTradeUserCountInfo(@Param("beginTradeDate") String beginTradeDate, @Param("endTradeDate") String endTradeDate);
}

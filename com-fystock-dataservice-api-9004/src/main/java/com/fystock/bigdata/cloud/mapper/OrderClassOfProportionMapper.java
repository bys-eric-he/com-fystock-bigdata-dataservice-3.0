package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.model.OrderSizeOfTotalSumAndCountInfo;
import com.fystock.bigdata.cloud.model.TickerQuatInfo;
import com.fystock.bigdata.cloud.model.UpOfTotalSumAndCountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * N日增仓占比
 *
 * @author He.Yong
 * @since 2021-07-29 16:46:58
 */

@Mapper
public interface OrderClassOfProportionMapper {
    /**
     * 获取指定股票指定交易日内的成交明细
     *
     * @param stockCode
     * @param startTradeDate
     * @param endTradeDate
     * @return
     */
    List<TickerQuatInfo> getTickerQuatInfo(@Param("stockCode") String stockCode,
                                           @Param("startTradeDate") String startTradeDate,
                                           @Param("endTradeDate") String endTradeDate);

    /**
     * 获取指定股票指定交易日内的大中小单总金额和总笔数
     *
     * @param stockCode
     * @param startTradeDate
     * @param endTradeDate
     * @return
     */
    List<OrderSizeOfTotalSumAndCountInfo> getOrderSizeOfTotalSumAndCountInfo(@Param("stockCode") String stockCode,
                                                                             @Param("startTradeDate") String startTradeDate,
                                                                             @Param("endTradeDate") String endTradeDate);

    /**
     * 获取指定股票指定交易日内的主买、主卖、非主买、非主卖总金额和总笔数
     *
     * @param stockCode
     * @param startTradeDate
     * @param endTradeDate
     * @return
     */
    List<UpOfTotalSumAndCountInfo> getUpOfTotalSumAndCountInfo(@Param("stockCode") String stockCode,
                                                               @Param("startTradeDate") String startTradeDate,
                                                               @Param("endTradeDate") String endTradeDate);
}

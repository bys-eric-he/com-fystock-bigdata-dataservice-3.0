package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.model.TickerTradeQtyTotalCountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 指定交易日指定股票成交数量和金额
 */
@Mapper
public interface TickerTradeQtyTotalCountInfoMapper {
    /**
     * 获取指定交易日指定股票成交数量和金额
     *
     * @param tradeDate
     * @param stockCode
     * @return
     */
    TickerTradeQtyTotalCountInfo getTickerTradeQtyTotalCountInfo(@Param("tradeDate") String tradeDate,
                                                                 @Param("stockCode") String stockCode);
}

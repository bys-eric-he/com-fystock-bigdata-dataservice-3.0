package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.hbase.StockClosingPrice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 股票收盘价
 *
 * @author He.Yong
 * @since 2021-08-06 11:16:25
 */
@Mapper
public interface StockClosingPriceMapper {
    /**
     * 获取指定股票指定交易日的收盘价
     *
     * @param stockCode
     * @param tradeDate
     * @return
     */
    StockClosingPrice getStockClosingPriceInfo(@Param("stockCode") String stockCode,
                                               @Param("tradeDate") String tradeDate);
}

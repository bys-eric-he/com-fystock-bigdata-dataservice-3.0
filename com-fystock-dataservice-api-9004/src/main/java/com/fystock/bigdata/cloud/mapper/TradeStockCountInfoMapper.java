package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.model.TradeStockCountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 交易个股买入、卖出成交笔数走势
 *
 * @author He.Yong
 * @since 2021-06-23 11:07:25
 */
@Mapper
public interface TradeStockCountInfoMapper {
    /**
     * 获取指定交易日买入、卖出笔数走势数据
     *
     * @param tradeDate
     * @return
     */
    List<TradeStockCountInfo> getTradeStockCountInfo(@Param("tradeDate") String tradeDate);

    /**
     * 获取近一个月交易日买入、卖出笔数走势数据
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @return
     */
    List<TradeStockCountInfo> getLastMonthTradeTradeStockCountInfo(@Param("beginTradeDate") String beginTradeDate, @Param("endTradeDate") String endTradeDate);
}

package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.model.TradeInduInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 港股行业交易量排行榜
 *
 * @author He.Yong
 * @since 2021-06-21 14:13:15
 */
@Mapper
public interface BigScreenTradeInduInfoMapper {
    /**
     * 获取指定交易日港股行业交易量Top10
     *
     * @param tradeDate
     * @param top
     * @return
     */
    List<TradeInduInfo> getTradeInduTopInfo(@Param("tradeDate") String tradeDate, @Param("top") Integer top);

    /**
     * 获取指定交易日区间港股行业交易量Top10
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @param top
     * @return
     */
    List<TradeInduInfo> getTradeInduTopInfoByInterval(@Param("beginTradeDate") String beginTradeDate, @Param("endTradeDate") String endTradeDate, @Param("top") Integer top);
}

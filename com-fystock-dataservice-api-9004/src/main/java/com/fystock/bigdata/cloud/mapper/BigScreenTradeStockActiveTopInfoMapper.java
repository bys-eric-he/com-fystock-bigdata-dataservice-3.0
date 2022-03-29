package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.redis.BigScreenTradeStockActiveTopInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 港股交易个股活跃TOP排行榜
 *
 * @author He.Yong
 * @since 2021-06-23 17:05:15
 */
@Mapper
public interface BigScreenTradeStockActiveTopInfoMapper {
    /**
     * 根据指定交易日获取交易活跃个股Top10
     *
     * @param tradeDate
     * @param top
     * @return
     */
    List<BigScreenTradeStockActiveTopInfo> getTradeStockActiveTopInfo(@Param("tradeDate") String tradeDate, @Param("top") Integer top);

    /**
     * 根据指定交易日区间获取交易活跃个股Top10
     *
     * @param beginTradeDate
     * @param endTradeDate
     * @param top
     * @return
     */
    List<BigScreenTradeStockActiveTopInfo> getTradeStockActiveTopInfoByInterval(@Param("beginTradeDate") String beginTradeDate, @Param("endTradeDate") String endTradeDate, @Param("top") Integer top);
}

package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.model.TradeCountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 港股交易个股数量统计
 *
 * @author He.Yong
 * @since 2021-06-21 11:15:28
 */
@Mapper
public interface BigScreenTradeCountInfoMapper {
    /**
     * 获取指定交易日个股数量
     *
     * @param tradeDate
     * @return
     */
    List<TradeCountInfo> getTradeCountInfo(@Param("tradeDate") String tradeDate);
}

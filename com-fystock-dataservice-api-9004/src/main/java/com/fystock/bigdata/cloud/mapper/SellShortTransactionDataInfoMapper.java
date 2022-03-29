package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.clickhouse.SellShortTransactionDataInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 即日卖空成交数据
 *
 * @author He.Yong
 * @since 2021-08-04 17:52:36
 */
@Mapper
public interface SellShortTransactionDataInfoMapper {
    /**
     * 获取指定交易日卖空成交数据
     *
     * @param tradeDate
     * @return
     */
    List<SellShortTransactionDataInfo> getSellShortTransactionDataList(@Param("tradeDate") String tradeDate);

    /**
     * 获取指定交易日\指定股票卖空成交数据
     *
     * @param tradeDate
     * @param stockCode
     * @return
     */
    SellShortTransactionDataInfo getSellShortTransactionDataInfo(@Param("tradeDate") String tradeDate,
                                                                 @Param("stockCode") String stockCode);

    /**
     * 持久化即日卖空成交数据
     *
     * @param sellShortTransactionDataInfos
     */
    void insertSellShortTransactionDataInfo(@Param("sellShortTransactionDataInfos") List<SellShortTransactionDataInfo> sellShortTransactionDataInfos);
}

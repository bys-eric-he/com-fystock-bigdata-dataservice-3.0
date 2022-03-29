package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.TotalTradeNumberAmountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 每日交易总笔数、交易总金额数统计Mapper
 *
 * @author He.Yong
 * @since 2021-03-12 11:29:19
 */
@Mapper
public interface TotalTradeNumberAmountInfoMapper {

    /**
     * 获取每日交易总笔数、交易总金额数
     *
     * @return
     */
    List<TotalTradeNumberAmountInfo> findAllTotalTradeNumberAmountInfo();

    /**
     * 根据导入日期获取每日交易总笔数、交易总金额数
     *
     * @param importDateTime
     * @return
     */
    List<TotalTradeNumberAmountInfo> findAllTotalTradeNumberAmountInfoByImportDateTime(@Param("importDateTime") String importDateTime);
}

package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.TotalTradeNumberAmountAllInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 累计交易总笔数、交易总金额数统计Mapper
 *
 * @author He.Yong
 * @since 2021-03-12 10:29:19
 */
@Mapper
public interface TotalTradeNumberAmountAllInfoMapper {
    /**
     * 获取累计交易总笔数、交易总金额数
     *
     * @return
     */
    List<TotalTradeNumberAmountAllInfo> findAllTotalTradeNumberAmountAllInfo();

    /**
     * 根据导入日期获取累计交易总笔数、交易总金额数
     *
     * @param importDateTime
     * @return
     */
    List<TotalTradeNumberAmountAllInfo> findAllTotalTradeNumberAmountAllInfoByImportDateTime(@Param("importDateTime") String importDateTime);
}

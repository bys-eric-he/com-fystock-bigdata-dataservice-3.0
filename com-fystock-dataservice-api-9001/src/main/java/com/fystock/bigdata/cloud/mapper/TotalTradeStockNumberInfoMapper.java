package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.TotalTradeStockNumberInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 股票交易次数统计Mapper
 *
 * @author He.Yong
 * @since 2021-03-12 13:19:19
 */
@Mapper
public interface TotalTradeStockNumberInfoMapper {
    /**
     * 根据指定交易日期, 获取股票交易次数 可视化数据
     *
     * @param tradeDate
     * @return
     */
    List<TotalTradeStockNumberInfo> findAllByTradeDateMax(@Param("tradeDate") String tradeDate);


    /**
     * 根据指定交易日期, 获取股票交易次数 可视化数据
     *
     * @param tradeDate
     * @return
     */
    List<TotalTradeStockNumberInfo> findAllByTradeDate(@Param("tradeDate") String tradeDate);

    /**
     * 根据导入日期及指定交易日期, 获取股票交易次数 可视化数据
     *
     * @param importDateTime
     * @param tradeDate
     * @return
     */
    List<TotalTradeStockNumberInfo> findAllByImportDateTimeAndTradeDate(@Param("importDateTime") String importDateTime, @Param("tradeDate") String tradeDate);
}

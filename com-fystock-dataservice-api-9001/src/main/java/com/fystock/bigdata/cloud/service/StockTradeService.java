package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.model.TotalTradeNumberAmountAllInfoModel;
import com.fystock.bigdata.cloud.model.TotalTradeNumberAmountInfoModel;
import com.fystock.bigdata.cloud.model.TotalTradeStockNumberInfoModel;

import java.util.List;
/**
 * 交易相关信息业务处理
 *
 * @author He.Yong
 * @since 2021-04-26 17:46:00
 */
public interface StockTradeService {

    /**
     * 获取每日交易总笔数、交易总金额数
     *
     * @return
     */
    List<TotalTradeNumberAmountInfoModel> findAllTotalTradeNumberAmountInfo();

    /**
     * 根据导入日期获取每日交易总笔数、交易总金额数
     *
     * @param importDateTime
     * @return
     */
    List<TotalTradeNumberAmountInfoModel> findAllTotalTradeNumberAmountInfoByImportDateTime(String importDateTime);

    /**
     * 获取累计交易总笔数、交易总金额数
     *
     * @return
     */
    List<TotalTradeNumberAmountAllInfoModel> findAllTotalTradeNumberAmountAllInfo();

    /**
     * 根据导入日期获取累计交易总笔数、交易总金额数
     *
     * @param importDateTime
     * @return
     */
    List<TotalTradeNumberAmountAllInfoModel> findAllTotalTradeNumberAmountAllInfoByImportDateTime(String importDateTime);

    /**
     * 根据指定交易日期, 获取最后一次统计时间的股票交易次数 可视化数据
     *
     * @param tradeDate
     * @return
     */
    List<TotalTradeStockNumberInfoModel> findAllByTradeDateMax(String tradeDate);

    /**
     * 根据指定交易日期, 获取股票交易次数 可视化数据
     *
     * @param tradeDate
     * @return
     */
    List<TotalTradeStockNumberInfoModel> findAllByTradeDate(String tradeDate);

    /**
     * 根据导入日期及指定交易日期, 获取股票交易次数 可视化数据
     *
     * @param importDateTime
     * @param tradeDate
     * @return
     */
    List<TotalTradeStockNumberInfoModel> findAllByImportDateTimeAndTradeDate(String importDateTime, String tradeDate);
}

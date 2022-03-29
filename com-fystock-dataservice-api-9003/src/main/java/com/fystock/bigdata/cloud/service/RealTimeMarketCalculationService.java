package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.model.RealTimeMarketCalculationModel;

import java.util.List;
import java.util.Map;

/**
 * 大盘云图实时行情计算结果查询服务
 *
 * @author He.Yong
 * @since 2021-05-20 10:58:29
 */
public interface RealTimeMarketCalculationService {
    /**
     * 获取Redis缓存中所有版块中每只股票最后一次计算结果
     *
     * @return
     */
    Map<String, List<RealTimeMarketCalculationModel>> getMarketDataLatestFromCache();

    /**
     * 按指定交易日获取所有版块中每只股票最后一次计算结果
     *
     * @param tradeDate
     * @return
     */
    Map<String, List<RealTimeMarketCalculationModel>> getMarketDataByTradeDate(String tradeDate);

    /**
     * 按指定交易日获取所有版块中每只股票最后一次计算结果 -定时器调用
     *
     * @param tradeDate
     * @return
     */
    Map<String, List<RealTimeMarketCalculationModel>> getMarketDataByTradeDateForTimer(String tradeDate);

    /**
     * 按指定交易日和版块代码获取每只股票最后一次计算结果
     *
     * @param tradeDate
     * @return
     */
    Map<String, List<RealTimeMarketCalculationModel>> getMarketDataByTradeDateAndSectionCode(String tradeDate, String sectionCode);

    /**
     * 按指定交易日和版块代码获取市值最大的Top N只股票最后一次计算结果
     *
     * @param tradeDate
     * @return
     */
    Map<String, List<RealTimeMarketCalculationModel>> getMarketDataByTradeDateAndSectionCodeAndTopN(String tradeDate, String sectionCode, int top);
}

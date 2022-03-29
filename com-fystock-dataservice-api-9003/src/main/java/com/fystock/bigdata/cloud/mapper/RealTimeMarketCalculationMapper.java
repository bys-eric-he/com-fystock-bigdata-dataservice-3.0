package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.hbase.RealTimeMarketCalculation;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 实时行情计算结果查询Mapper接口
 *
 * @author He.Yong
 * @since 2021-03-15 13:33:49
 */
@Mapper
public interface RealTimeMarketCalculationMapper {
    /**
     * 按指定交易日获取所有版块中每只股票最后一次计算结果
     *
     * @param tradeDate
     * @return
     */
    List<RealTimeMarketCalculation> getMarketDataByTradeDate(@Param("tradeDate") String tradeDate);

    /**
     * 按指定交易日和版块代码获取每只股票最后一次计算结果
     *
     * @param tradeDate
     * @return
     */
    List<RealTimeMarketCalculation> getMarketDataByTradeDateAndSectionCode(@Param("tradeDate") String tradeDate, @Param("sectionCode") String sectionCode);

    /**
     * 按指定交易日和版块代码获取市值最大的Top N只股票最后一次计算结果
     *
     * @param tradeDate
     * @return
     */
    List<RealTimeMarketCalculation> getMarketDataByTradeDateAndSectionCodeAndTopN(@Param("tradeDate") String tradeDate, @Param("sectionCode") String sectionCode, @Param("top") int top);
}

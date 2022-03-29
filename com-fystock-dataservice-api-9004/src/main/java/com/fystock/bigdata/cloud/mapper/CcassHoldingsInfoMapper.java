package com.fystock.bigdata.cloud.mapper;


import com.fystock.bigdata.cloud.entity.clickhouse.CcassHoldingsInfo;
import com.fystock.bigdata.cloud.model.CcassHoldingsRatioInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CCASS系统同步数据
 *
 * @author He.Yong
 * @since 2021-08-11 15:51:48
 */
@Mapper
public interface CcassHoldingsInfoMapper {

    /**
     * 持久化CCASS持仓信息
     *
     * @param ccassHoldingsInfos
     */
    void insertCcassHoldingsInfos(@Param("ccassHoldingsInfos") List<CcassHoldingsInfo> ccassHoldingsInfos);

    /**
     * 持久化CCASS持仓信息比例计算结果
     *
     * @param ccassHoldingsRatioInfos
     */
    void insertCcassHoldingsRatioInfos(@Param("ccassHoldingsRatioInfos") List<CcassHoldingsRatioInfo> ccassHoldingsRatioInfos);

    /**
     * 获取指定日期的持仓信息
     *
     * @param updateDate
     * @return
     */
    List<CcassHoldingsInfo> getCcassHoldingsInfoList(@Param("updateDate") String updateDate);

    /**
     * 获取指定日期CCASS持仓数据和某个基准时刻的持股变动
     *
     * @param updateDate
     * @param stockCode
     * @return
     */
    CcassHoldingsInfo getCcassHoldingsInfo(@Param("updateDate") String updateDate, @Param("stockCode") String stockCode);

    /**
     * 获取指定日期的CCASS参与者持仓信息比例
     *
     * @param updateDate
     * @return
     */
    List<CcassHoldingsRatioInfo> getCcassHoldingsRatioInfoList(@Param("updateDate") String updateDate);
}

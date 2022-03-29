package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.entity.clickhouse.CcassHoldingsInfo;
import com.fystock.bigdata.cloud.model.CcassHoldingsChangeInfo;
import com.fystock.bigdata.cloud.model.CcassHoldingsRatioInfo;

import java.util.List;

/**
 * CCASS持仓信息
 *
 * @author He.Yong
 * @since 2021-08-11 16:12:18
 */
public interface CcassHoldingsInfoService {
    /**
     * 持久化CCASS持仓信息
     *
     * @param ccassHoldingsInfos
     */
    int insertCcassHoldingsInfos(List<CcassHoldingsInfo> ccassHoldingsInfos);

    /**
     * 持久化CCASS持仓信息比例计算结果
     *
     * @param ccassHoldingsRatioInfos
     */
    int insertCcassHoldingsRatioInfos(List<CcassHoldingsRatioInfo> ccassHoldingsRatioInfos);

    /**
     * 获取指定日期的持仓信息
     *
     * @param updateDate
     * @return
     */
    List<CcassHoldingsInfo> getCcassHoldingsInfoList(String updateDate);

    /**
     * 获取指定日期指定股票的持仓信息
     *
     * @param updateDate
     * @param stockCode
     * @return
     */
    CcassHoldingsInfo getCcassHoldingsInfo(String updateDate, String stockCode);

    /**
     * 获取指定日期的持仓信息比例
     *
     * @param updateDate
     * @return
     */
    List<CcassHoldingsRatioInfo> getCcassHoldingsRatioInfoList(String updateDate);

    /**
     * 获取指定日期CCASS持仓数据和某个基准时刻的持股变动
     *
     * @param holdDateA
     * @param holdDateB
     * @param stockCode
     * @return
     */
    CcassHoldingsChangeInfo getCcassHoldingsChangeInfo(String holdDateA, String holdDateB, String stockCode);
}

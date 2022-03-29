package com.fystock.bigdata.cloud.service.impl;

import com.fystock.bigdata.cloud.annotation.Log4FYStock;
import com.fystock.bigdata.cloud.entity.clickhouse.CcassHoldingsInfo;
import com.fystock.bigdata.cloud.mapper.CcassHoldingsInfoMapper;
import com.fystock.bigdata.cloud.model.CcassHoldingsChangeInfo;
import com.fystock.bigdata.cloud.model.CcassHoldingsRatioInfo;
import com.fystock.bigdata.cloud.service.CcassHoldingsInfoService;
import com.fystock.bigdata.cloud.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * CCASS持仓信息
 *
 * @author He.Yong
 * @since 2021-08-11 16:13:28
 */
@Slf4j
@Service
public class CcassHoldingsInfoServiceImpl implements CcassHoldingsInfoService {

    @Autowired
    private CcassHoldingsInfoMapper ccassHoldingsInfoMapper;

    /**
     * 持久化CCASS持仓信息
     *
     * @param ccassHoldingsInfos
     */
    @Log4FYStock("持久化CCASS持仓信息")
    @Override
    public int insertCcassHoldingsInfos(List<CcassHoldingsInfo> ccassHoldingsInfos) {
        ccassHoldingsInfoMapper.insertCcassHoldingsInfos(ccassHoldingsInfos);
        int rows = ccassHoldingsInfos.size();
        log.info("-----插入CCASS持仓信息完成! 影响行数: {} 行!", rows);
        return rows;
    }

    /**
     * 持久化CCASS持仓信息比例计算结果
     *
     * @param ccassHoldingsRatioInfos
     */
    @Log4FYStock("持久化CCASS持仓信息比例计算结果")
    @Override
    public int insertCcassHoldingsRatioInfos(List<CcassHoldingsRatioInfo> ccassHoldingsRatioInfos) {
        ccassHoldingsRatioInfos.forEach(o -> o.setCreateTime(DateTimeUtil.getCurrentDateTime()));
        ccassHoldingsInfoMapper.insertCcassHoldingsRatioInfos(ccassHoldingsRatioInfos);
        int rows = ccassHoldingsRatioInfos.size();
        log.info("-----持久化CCASS持仓信息比例计算结果完成! 影响行数: {} 行!", rows);
        return rows;
    }

    /**
     * 获取指定日期的持仓信息
     *
     * @param updateDate
     * @return
     */
    @Log4FYStock("获取指定日期的持仓信息")
    @Override
    public List<CcassHoldingsInfo> getCcassHoldingsInfoList(String updateDate) {
        return ccassHoldingsInfoMapper.getCcassHoldingsInfoList(updateDate);
    }

    /**
     * 获取指定日期、指定股票的持仓信息
     *
     * @param updateDate
     * @param stockCode
     * @return
     */
    @Log4FYStock("获取指定日期、指定股票的持仓信息")
    @Override
    public CcassHoldingsInfo getCcassHoldingsInfo(String updateDate, String stockCode) {
        return ccassHoldingsInfoMapper.getCcassHoldingsInfo(updateDate, stockCode);
    }

    /**
     * 获取指定日期的持仓信息比例
     *
     * @param updateDate
     * @return
     */
    @Log4FYStock("获取指定日期的持仓信息比例")
    @Override
    public List<CcassHoldingsRatioInfo> getCcassHoldingsRatioInfoList(String updateDate) {
        return ccassHoldingsInfoMapper.getCcassHoldingsRatioInfoList(updateDate);
    }

    /**
     * 获取指定日期CCASS持仓数据和某个基准时刻的持股变动
     *
     * @param holdDateA
     * @param holdDateB
     * @param stockCode
     * @return
     */
    @Log4FYStock("获取指定日期CCASS持仓数据和某个基准时刻的持股变动")
    @Override
    public CcassHoldingsChangeInfo getCcassHoldingsChangeInfo(String holdDateA, String holdDateB, String stockCode) {

        CcassHoldingsInfo ccassHoldingsInfoA = ccassHoldingsInfoMapper.getCcassHoldingsInfo(holdDateA, stockCode);
        CcassHoldingsInfo ccassHoldingsInfoB = ccassHoldingsInfoMapper.getCcassHoldingsInfo(holdDateB, stockCode);

        if (ccassHoldingsInfoA == null || ccassHoldingsInfoB == null) {
            return null;
        }

        //计算持股变动
        BigDecimal changes = ccassHoldingsInfoA.getStockHolding().subtract(ccassHoldingsInfoB.getStockHolding());

        CcassHoldingsChangeInfo ccassHoldingsChangeInfo = new CcassHoldingsChangeInfo();
        ccassHoldingsChangeInfo.setStockCode(stockCode);
        ccassHoldingsChangeInfo.setStockHoldingA(ccassHoldingsInfoA.getStockHolding());
        ccassHoldingsChangeInfo.setStockHoldingB(ccassHoldingsInfoB.getStockHolding());
        ccassHoldingsChangeInfo.setStockNameEn(ccassHoldingsInfoA.getStockNameEn());
        ccassHoldingsChangeInfo.setHoldDateA(holdDateA);
        ccassHoldingsChangeInfo.setHoldDateB(holdDateB);
        ccassHoldingsChangeInfo.setChanges(changes);

        return ccassHoldingsChangeInfo;
    }
}

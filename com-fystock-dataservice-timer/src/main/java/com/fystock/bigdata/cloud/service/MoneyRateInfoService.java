package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.entity.mysql.MoneyRateInfo;

import java.util.List;

/**
 * 货币汇率信息表
 *
 * @author He.Yong
 * @since 2021-09-10 14:47:25
 */
public interface MoneyRateInfoService {
    /**
     * 获取所有货币汇率信息
     *
     * @return
     */
    List<MoneyRateInfo> getAllMoneyRateInfoList();

    /**
     * 清空所有货币汇率信息
     *
     * @return
     */
    int truncateMoneyRateInfo();

    /**
     * 插入货币汇率信息
     *
     * @param moneyRateInfos
     * @return
     */
    int insertMoneyRateInfoList(List<MoneyRateInfo> moneyRateInfos);
}

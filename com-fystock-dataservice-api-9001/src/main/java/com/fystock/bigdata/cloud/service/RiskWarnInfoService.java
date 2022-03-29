package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.model.RiskWarnInfoModel;

import java.util.List;

/**
 * 风控预警信息
 *
 * @author He.Yong
 * @since 2021-04-20 16:41:00
 */
public interface RiskWarnInfoService {
    /**
     * 获取当前最新预警信息
     *
     * @return
     */
    List<RiskWarnInfoModel> getAllLatestInfo();

    /**
     * 获取当前最新指定预警信息
     *
     * @return
     */
    List<RiskWarnInfoModel> getAllLatestInfo(Integer... items);

    /**
     * 获取指定导入日期的风险预警信息
     *
     * @param importDateTime
     * @return
     */
    List<RiskWarnInfoModel> getAllByImportDateTime(String importDateTime);

    /**
     * 获取指定导入日期的指定风险预警信息
     *
     * @param importDateTime
     * @param items
     * @return
     */
    List<RiskWarnInfoModel> getAllByImportDateTime(String importDateTime, Integer... items);
}

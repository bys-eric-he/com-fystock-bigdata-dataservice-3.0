package com.fystock.bigdata.cloud.service.impl;

import com.fystock.bigdata.cloud.annotation.Log4FYStock;
import com.fystock.bigdata.cloud.mapper.RiskWarnInfoMapper;
import com.fystock.bigdata.cloud.entity.mysql.RiskWarnInfo;
import com.fystock.bigdata.cloud.mapping.RiskWarnInfoMapping;
import com.fystock.bigdata.cloud.model.RiskWarnInfoModel;
import com.fystock.bigdata.cloud.service.RiskWarnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 风控预警信息业务处理
 *
 * @author He.Yong
 * @since 2021-03-22 17:46:00
 */
@Service
public class RiskWarnInfoServiceImpl implements RiskWarnInfoService {

    private RiskWarnInfoMapper riskWarnInfoMapper;

    @Autowired
    public RiskWarnInfoServiceImpl(RiskWarnInfoMapper riskWarnInfoMapper) {
        this.riskWarnInfoMapper = riskWarnInfoMapper;
    }

    /**
     * 获取当前最新预警信息
     *
     * @return
     */
    @Log4FYStock(value = "获取当前最新预警信息")
    @Override
    public List<RiskWarnInfoModel> getAllLatestInfo() {

        List<RiskWarnInfo> riskWarnInfos = riskWarnInfoMapper.getAllLatestInfo();

        List<RiskWarnInfoModel> results = new ArrayList<>();
        riskWarnInfos.forEach(o -> results.add(RiskWarnInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 获取当前最新指定预警信息
     *
     * @param items
     * @return
     */
    @Override
    public List<RiskWarnInfoModel> getAllLatestInfo(Integer... items) {
        List<RiskWarnInfo> riskWarnInfos = riskWarnInfoMapper.getAllLatestInfoByPId(items);

        List<RiskWarnInfoModel> results = new ArrayList<>();
        riskWarnInfos.forEach(o -> results.add(RiskWarnInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 获取指定导入日期的风险预警信息
     *
     * @param importDateTime
     * @return
     */
    @Log4FYStock(value = "获取指定导入日期的风险预警信息")
    @Override
    public List<RiskWarnInfoModel> getAllByImportDateTime(String importDateTime) {
        List<RiskWarnInfo> riskWarnInfos = riskWarnInfoMapper.getAllByImportDateTime(importDateTime);

        List<RiskWarnInfoModel> results = new ArrayList<>();
        riskWarnInfos.forEach(o -> results.add(RiskWarnInfoMapping.toModel(o)));

        return results;
    }

    /**
     * 获取指定导入日期的指定风险预警信息
     *
     * @param importDateTime
     * @param items
     * @return
     */
    @Override
    public List<RiskWarnInfoModel> getAllByImportDateTime(String importDateTime, Integer... items) {
        List<RiskWarnInfo> riskWarnInfos = riskWarnInfoMapper.getAllByImportDateTimeAndPId(importDateTime, items);

        List<RiskWarnInfoModel> results = new ArrayList<>();
        riskWarnInfos.forEach(o -> results.add(RiskWarnInfoMapping.toModel(o)));

        return results;
    }
}

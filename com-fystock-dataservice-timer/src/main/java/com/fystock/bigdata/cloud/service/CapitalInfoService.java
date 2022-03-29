package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.entity.mysql.CapitalInfo;

import java.util.List;

/**
 * 股本信息
 *
 * @author He.Yong
 * @since 2021-08-11 14:54:18
 */
public interface CapitalInfoService {
    /**
     * 获取所有股本信息
     *
     * @return
     */
    List<CapitalInfo> getAllCapitalInfoSourceList();

    /**
     * 清空表数据
     *
     * @return
     */
    int truncateCapitalInfoTable();

    /**
     * 批量插入股本信息
     *
     * @param capitalInfoList
     * @return
     */
    int insertCapitalInfoBatch(List<CapitalInfo> capitalInfoList);
}

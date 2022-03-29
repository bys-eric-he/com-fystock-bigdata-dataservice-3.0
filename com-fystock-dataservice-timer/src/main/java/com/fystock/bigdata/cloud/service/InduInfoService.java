package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.entity.mysql.InduDataInfo;
import com.fystock.bigdata.cloud.entity.sqlserver.InduSourceInfo;

import java.util.List;

public interface InduInfoService {

    /**
     * 获取当前所有行业股票分类列表
     *
     * @return
     */
    List<InduDataInfo> getAllInduInfoDataList();

    /**
     * 获取第三方所有行业股票分类列表
     *
     * @return
     */
    List<InduSourceInfo> getAllInduInfoSourceList();

    /**
     * 批量更新或保存第三方行业和股票分类到本地
     *
     * @param induInfoDataEntities
     * @param induInfoSourceEntities
     * @return
     */
    boolean saveOrUpdateInduInfo(List<InduDataInfo> induInfoDataEntities, List<InduSourceInfo> induInfoSourceEntities);
}

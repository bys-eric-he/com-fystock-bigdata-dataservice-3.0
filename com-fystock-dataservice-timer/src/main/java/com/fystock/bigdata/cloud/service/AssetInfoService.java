package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.entity.mysql.AssetInfo;

import java.util.List;

/**
 * 股票基本信息
 *
 * @author He.Yong
 * @since 2021-08-11 14:54:28
 */
public interface AssetInfoService {
    /**
     * 获取所有股票信息
     *
     * @return
     */
    List<AssetInfo> getAllAssetInfoSourceList();

    /**
     * 清空表数据
     *
     * @return
     */
    int truncateAssetInfoTable();

    /**
     * 批量插入股票信息
     *
     * @return
     */
    int insertAssetInfoBatch(List<AssetInfo> assetInfoList);
}

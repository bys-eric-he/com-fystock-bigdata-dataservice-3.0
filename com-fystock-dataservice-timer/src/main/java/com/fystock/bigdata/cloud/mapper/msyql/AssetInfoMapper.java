package com.fystock.bigdata.cloud.mapper.msyql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fystock.bigdata.cloud.entity.mysql.AssetInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssetInfoMapper  extends BaseMapper<AssetInfo> {
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

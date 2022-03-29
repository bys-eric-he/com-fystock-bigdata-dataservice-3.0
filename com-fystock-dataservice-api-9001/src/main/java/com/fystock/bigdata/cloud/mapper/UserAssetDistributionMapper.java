package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.UserAssetDistributionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户资产规模人数
 *
 * @author He.Yong
 * @since 2021-09-13 15:12:20
 */
@Mapper
public interface UserAssetDistributionMapper {
    /**
     * 获取各类用户资产规模人数
     *
     * @return
     */
    List<UserAssetDistributionInfo> findAllUserAssetDistributionInfo();

    /**
     * 根据导入日期获取各类用户资产规模人数
     *
     * @param importDateTime
     * @return
     */
    List<UserAssetDistributionInfo> findAllUserAssetDistributionInfoByImportDateTime(@Param("importDateTime") String importDateTime);
}

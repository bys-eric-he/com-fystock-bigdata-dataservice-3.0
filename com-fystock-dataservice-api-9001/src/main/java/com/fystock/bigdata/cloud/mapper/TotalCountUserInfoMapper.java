package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.TotalCountUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 各类累计用户数统计Mapper
 *
 * @author He.Yong
 * @since 2021-03-12 15:29:19
 */
@Mapper
public interface TotalCountUserInfoMapper {
    /**
     * 获取各类累计用户数统计
     *
     * @return
     */
    List<TotalCountUserInfo> findAllTotalCountUserInfo();

    /**
     * 根据导入日期获取各类累计用户数统计
     *
     * @param importDateTime
     * @return
     */
    List<TotalCountUserInfo> findAllTotalCountUserInfoByImportDateTime(@Param("importDateTime") String importDateTime);
}

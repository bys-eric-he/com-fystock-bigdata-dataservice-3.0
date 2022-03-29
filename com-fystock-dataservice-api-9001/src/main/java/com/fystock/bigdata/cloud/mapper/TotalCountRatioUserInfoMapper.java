package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.TotalCountRatioUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户数占注册总人数比率(转化率)统计Mapper
 *
 * @author He.Yong
 * @since 2021-03-12 15:21:19
 */
@Mapper
public interface TotalCountRatioUserInfoMapper {
    /**
     * 获取各类用户数占注册总人数比率(转化率)
     *
     * @return
     */
    List<TotalCountRatioUserInfo> findAllTotalCountRatioUserInfo();

    /**
     * 根据导入日期, 获取各类用户数占注册总人数比率(转化率)
     *
     * @param importDateTime
     * @return
     */
    List<TotalCountRatioUserInfo> findAllTotalCountRatioUserInfoByImportDateTime(@Param("importDateTime") String importDateTime);
}

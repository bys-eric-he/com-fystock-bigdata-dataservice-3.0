package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.GenderCountRatioUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 每日注册人数性别比率统计Mapper
 *
 * @author He.Yong
 * @since 2021-03-15 10:33:23
 */
@Mapper
public interface GenderCountRatioUserInfoMapper {

    /**
     * 获取每日注册人数性别比率
     *
     * @return
     */
    List<GenderCountRatioUserInfo> findAllGenderCountRatioUserInfo();

    /**
     * 根据导入日期获取每日注册人数性别比率
     *
     * @param importDateTime
     * @return
     */
    List<GenderCountRatioUserInfo> findAllByImportDateTime(@Param("importDateTime") String importDateTime);
}

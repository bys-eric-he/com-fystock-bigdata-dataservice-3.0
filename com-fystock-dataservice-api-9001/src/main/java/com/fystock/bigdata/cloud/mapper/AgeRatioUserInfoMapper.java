package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.AgeRatioUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 注册用户年龄统计类Mapper
 *
 * @author He.Yong
 * @since 2021-03-15 09:56:21
 */
@Mapper
public interface AgeRatioUserInfoMapper {
    /**
     * 获取注册用户年龄段占总人数比率
     *
     * @return
     */
    List<AgeRatioUserInfo> findAllAgeRatioUser();

    /**
     * 根据导入日期, 获取注册用户年龄段占总人数比率
     *
     * @param importDateTime
     * @return
     */
    List<AgeRatioUserInfo> findAllAgeRatioUserByImportDateTime(@Param("importDateTime") String importDateTime);
}

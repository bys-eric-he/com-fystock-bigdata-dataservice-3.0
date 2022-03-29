package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.CityCountRatioUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 注册人数按城市分布比率统计类Mapper
 *
 * @author He.Yong
 * @since 2021-03-15 10:16:21
 */
@Mapper
public interface CityCountRatioUserInfoMapper {

    /**
     * 获取注册人数按城市分布比率
     *
     * @return
     */
    List<CityCountRatioUserInfo> findAllCityCountRatioUser();

    /**
     * 根据导入日期获取注册人数按城市分布比率
     *
     * @param importDateTime
     * @return
     */
    List<CityCountRatioUserInfo> findAllByImportDateTime(@Param("importDateTime") String importDateTime);
}

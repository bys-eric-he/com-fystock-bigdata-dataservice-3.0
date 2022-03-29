package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.RiskWarnInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CUBP用户风险控制预警信息Mapper
 *
 * @author He.Yong
 * @since 2021-04-21 13:21:19
 */
@Mapper
public interface RiskWarnInfoMapper {
    /**
     * 获取当前最新预警信息
     *
     * @return
     */
    List<RiskWarnInfo> getAllLatestInfo();

    /**
     * 获取当前最新预警信息
     *
     * @param items
     * @return
     */
    List<RiskWarnInfo> getAllLatestInfoByPId(@Param("items") Integer... items);

    /**
     * 获取指定导入日期的风险预警信息
     *
     * @param importDateTime
     * @return
     */
    List<RiskWarnInfo> getAllByImportDateTime(@Param("importDateTime") String importDateTime);

    /**
     * 获取指定导入日期的风险预警信息
     *
     * @param importDateTime
     * @return
     */
    List<RiskWarnInfo> getAllByImportDateTimeAndPId(@Param("importDateTime") String importDateTime, @Param("items") Integer... items);
}

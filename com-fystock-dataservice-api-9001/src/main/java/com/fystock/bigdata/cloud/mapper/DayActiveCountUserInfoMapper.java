package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.DayActiveCountUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 日活跃用户数统计类Mapper
 *
 * @author He.Yong
 * @since 2021-03-15 10:23:21
 */
@Mapper
public interface DayActiveCountUserInfoMapper {
    /**
     * 根据指定时间区间获取日活跃用户数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<DayActiveCountUserInfo> findDayActiveCountUserInfo(@Param("startDate") String startDate, @Param("endDate") String endDate);
}

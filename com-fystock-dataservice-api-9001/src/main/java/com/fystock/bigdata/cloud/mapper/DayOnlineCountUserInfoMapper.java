package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.mysql.DayOnlineCountUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 每天在线用户数统计Mapper
 *
 * @author He.Yong
 * @since 2021-03-15 10:25:14
 */
@Mapper
public interface DayOnlineCountUserInfoMapper {

    /**
     * 根据指定时间区间获取每天在线用户数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<DayOnlineCountUserInfo> findDayOnlineCountUserInfo(@Param("startDate") String startDate, @Param("endDate") String endDate);
}

package com.fystock.bigdata.cloud.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 每天在线用户数统计 可视化数据
 *
 * @author He.Yong
 * @since 2021-04-16 11:23:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayOnlineCountUserInfo extends BaseInfo {
    /**
     * 当前年月日
     */
    private String currentDay;

    /**
     * 当前年月日在线人数
     */
    private Integer currentDayCount;
}

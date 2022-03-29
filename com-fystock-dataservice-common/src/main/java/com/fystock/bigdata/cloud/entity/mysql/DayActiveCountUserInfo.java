package com.fystock.bigdata.cloud.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 每天活跃用户数统计 可视化数据
 *
 * @author He.Yong
 * @since 2021-04-15 15:23:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayActiveCountUserInfo extends BaseInfo {
    /**
     * 当前年月日
     */
    private String currentDay;

    /**
     * 当前年月日活跃用户人数
     */
    private Integer currentDayCount;
}

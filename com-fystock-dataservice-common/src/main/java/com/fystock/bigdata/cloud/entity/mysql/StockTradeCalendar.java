package com.fystock.bigdata.cloud.entity.mysql;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 交易日日历实体对象
 *
 * @author He.Yong
 * @since 2021-04-22 09:19:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockTradeCalendar {
    /**
     * 交易日历ID
     */
    private int calendarId;

    /**
     * 自然日期
     */
    private Date normalDate;

    /**
     * 地区
     */
    private String regionCode;

    /**
     * 当天是否为交易日
     */
    private Byte isTradeDay;

    /**
     * 上一个交易日
     */
    private Date lastTrade;

    /**
     * 下一个交易日
     */
    private Date nextTrade;

    /**
     * 是否本周最后一个交易日
     */
    private Byte isWeekEnd;

    /**
     * 是否本月最后一个交易日
     */
    private Byte isMonthEnd;

    /**
     * 是否本年最后一个交易日
     */
    private Byte isYearEnd;

    /**
     * 上周最后一个交易日
     */
    private Date lastWeekTrade;

    /**
     * 上月最后一个交易日
     */
    private Date lastMonthTrade;

    /**
     * 上年最后一个交易日
     */
    private Date lastYearTrade;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 更新日期
     */
    private Date updateTime;

    /**
     * 外部系统时间
     */
    private Date externalTime;

    /**
     * 备注
     */
    private String remark;
}
package com.fystock.bigdata.cloud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 交易日历表模型对象
 *
 * @author He.Yong
 * @since 2021-02-04 17:21:22
 */
@Data
@NoArgsConstructor
@ApiModel(value = "stockTradeCalendarModel", description = "交易日历表")
public class StockTradeCalendarModel implements Serializable {
    private static final long serialVersionUID = 8655851615225665214L;
    /**
     * 交易日历ID
     */
    @ApiModelProperty(value = "交易日历ID", name = "calendarId")
    private int calendarId;

    /**
     * 自然日期
     */
    @ApiModelProperty(value = "自然日期", name = "normalDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date normalDate;

    /**
     * 地区
     */
    @ApiModelProperty(value = "地区", name = "regionCode")
    private String regionCode;

    /**
     * 当天是否为交易日
     */
    @ApiModelProperty(value = "当天是否为交易日", name = "isTradeDay")
    private Byte isTradeDay;

    /**
     * 上一个交易日
     */
    @ApiModelProperty(value = "上一个交易日", name = "lastTrade")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lastTrade;

    /**
     * 下一个交易日
     */
    @ApiModelProperty(value = "下一个交易日", name = "nextTrade")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date nextTrade;

    /**
     * 是否本周最后一个交易日
     */
    @ApiModelProperty(value = "是否本周最后一个交易日", name = "isWeekEnd")
    private Byte isWeekEnd;

    /**
     * 是否本月最后一个交易日
     */
    @ApiModelProperty(value = "是否本月最后一个交易日", name = "isMonthEnd")
    private Byte isMonthEnd;

    /**
     * 是否本年最后一个交易日
     */
    @ApiModelProperty(value = "是否本年最后一个交易日", name = "isYearEnd")
    private Byte isYearEnd;

    /**
     * 上周最后一个交易日
     */
    @ApiModelProperty(value = "上周最后一个交易日", name = "lastWeekTrade")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lastWeekTrade;

    /**
     * 上月最后一个交易日
     */
    @ApiModelProperty(value = "上月最后一个交易日", name = "lastMonthTrade")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lastMonthTrade;

    /**
     * 上年最后一个交易日
     */
    @ApiModelProperty(value = "上年最后一个交易日", name = "lastYearTrade")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lastYearTrade;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期", name = "createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新日期
     */
    @ApiModelProperty(value = "更新日期", name = "updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 外部系统时间
     */
    @ApiModelProperty(value = "外部系统时间", name = "externalTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date externalTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;
}
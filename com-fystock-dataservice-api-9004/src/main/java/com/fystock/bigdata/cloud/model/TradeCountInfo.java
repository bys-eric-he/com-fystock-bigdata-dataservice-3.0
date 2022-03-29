package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 港股交易个股数量
 *
 * @author He.Yong
 * @since 2021-06-18 17:14:59
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "tradeCountInfo", description = "港股交易个股数量")
public class TradeCountInfo implements Serializable {

    private static final long serialVersionUID = 5623852256325363114L;

    /**
     * 窗口内港股交易个数
     */
    @ApiModelProperty(value = "窗口内港股交易个数", name = "tradeCount")
    private long tradeCount;
    /**
     * 开窗时间
     */
    @ApiModelProperty(value = "开窗时间", name = "startTime")
    private String startTime;
    /**
     * 关窗时间
     */
    @ApiModelProperty(value = "关窗时间", name = "endTime")
    private String endTime;
    /**
     * 交易日
     */
    @ApiModelProperty(value = "交易日YYYYMMDD", name = "date")
    private String date;
    /**
     * 关窗时间段 HH:mm
     */
    @ApiModelProperty(value = "关窗时间段 HH:mm", name = "date")
    private String time;
}

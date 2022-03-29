package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 交易个股买入、卖出成交笔数走势
 *
 * @author He.Yong
 * @since 2021-06-17 18:53:29
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "tradeStockCountInfo", description = "交易个股买入、卖出成交笔数走势")
public class TradeStockCountInfo  implements Serializable {

    private static final long serialVersionUID = 6698851254825363655L;
    /**
     * 交易日期
     */
    @ApiModelProperty(value = "交易日期", name = "date")
    private String date;

    /**
     * 关窗时间
     */
    @ApiModelProperty(value = "关窗时间", name = "endTime")
    private String endTime;

    /**
     * 开窗时间
     */
    @ApiModelProperty(value = "开窗时间", name = "startTime")
    private String startTime;

    /**
     * 关窗时间段 HH:mm
     */
    @ApiModelProperty(value = "关窗时间段 HH:mm", name = "time")
    private String time;

    /**
     * 买入成交笔数
     */
    @ApiModelProperty(value = "买入成交笔数", name = "tradeBuyCount")
    private long tradeBuyCount;

    /**
     * 卖出成交笔数
     */
    @ApiModelProperty(value = "卖出成交笔数", name = "tradeSaleCount")
    private long tradeSaleCount;

    /**
     * 第几次触发
     */
    @ApiModelProperty(value = "第几次触发", name = "version")
    private int version;
}

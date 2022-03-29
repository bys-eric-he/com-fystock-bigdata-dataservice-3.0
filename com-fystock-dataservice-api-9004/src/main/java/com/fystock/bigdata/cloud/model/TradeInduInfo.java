package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 港股行业交易量排行榜
 *
 * @author He.Yong
 * @since 2021-06-18 15:52:59
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "tradeInduInfo", description = "港股行业交易量排行榜")
public class TradeInduInfo implements Serializable {

    private static final long serialVersionUID = 5623851615225363114L;
    /**
     * 交易量
     */
    @ApiModelProperty(value = "交易量", name = "tradeCount")
    private long tradeCount;
    /**
     * 行业名称
     */
    @ApiModelProperty(value = "行业名称", name = "induName")
    private String induName;
    /**
     * 行业编号
     */
    @ApiModelProperty(value = "行业编号", name = "induCode")
    private String induCode;
    /**
     * 当前排名
     */
    @ApiModelProperty(value = "当前排名", name = "rank")
    private int rank;
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
     * 交易日期
     */
    @ApiModelProperty(value = "交易日期", name = "date")
    private String date;
    /**
     * 第几次触发
     */
    @ApiModelProperty(value = "第几次触发", name = "version")
    private int version;
}

package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 做空成本
 * 做空成本=某只股票一段时间区间做空的金额/某只股票一段时间区间做空的成交量
 *
 * @author He.Yong
 * @since 2021-08-05 17:12:25
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "sellShortCostInfo", description = "股票做空成本")
public class SellShortCostInfo {
    /**
     * 股票ID
     */
    @ApiModelProperty(value = "股票代码.市场", name = "assetId")
    private String assetId;

    /**
     * 股票代码
     */
    @ApiModelProperty(value = "股票代码", name = "stockCode")
    private String stockCode;

    /**
     * 股票简称
     */
    @ApiModelProperty(value = "股票简称", name = "stockName")
    private String stockName;

    /**
     * 交易日期
     */
    @ApiModelProperty(value = "交易日期", name = "tradeDate")
    private String tradeDate;

    /**
     * 做空成本
     */
    @ApiModelProperty(value = "做空成本", name = "cost")
    private BigDecimal cost;
}

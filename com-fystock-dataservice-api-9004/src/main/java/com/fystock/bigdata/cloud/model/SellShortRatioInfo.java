package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 股票做空占比
 * 做空占比=某只股票一段时间区间做空的股数/该段时间区间总成交股数
 *
 * @author He.Yong
 * @since 2021-08-05 16:07:15
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "sellShortRatioInfo", description = "股票做空占比")
public class SellShortRatioInfo {

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
     * 做空占比
     */
    @ApiModelProperty(value = "做空占比", name = "ratio")
    private BigDecimal ratio;
}

package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "stockRelativeAbsoluteIndexModel", description = "两只股票相对强势指数")
public class StockRelativeAbsoluteIndexModel {
    /**
     * 股票A
     */
    @ApiModelProperty(value = "股票代码A", name = "stockCodeA")
    private String stockCodeA;
    /**
     * 股票A收盘价
     */
    @ApiModelProperty(value = "股票代码A收盘价", name = "stockPriceA")
    private BigDecimal stockPriceA;
    /**
     * 股票B
     */
    @ApiModelProperty(value = "股票代码B", name = "stockCodeB")
    private String stockCodeB;
    /**
     * 股票B收盘价
     */
    @ApiModelProperty(value = "股票代码B收盘价", name = "stockPriceB")
    private BigDecimal stockPriceB;
    /**
     * 交易日
     */
    @ApiModelProperty(value = "交易日", name = "tradeDate")
    private String tradeDate;
    /**
     * 相对强势指数
     */
    @ApiModelProperty(value = "相对强势指数", name = "index")
    private BigDecimal index;
}

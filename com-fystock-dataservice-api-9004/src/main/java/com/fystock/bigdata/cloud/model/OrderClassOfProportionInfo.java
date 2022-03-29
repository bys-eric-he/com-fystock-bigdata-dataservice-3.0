package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * N 日大单增仓占比
 *
 * @author He.Yong
 * @since 2021-07-29 16:33:25
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "orderClassOfProportionInfo", description = "N 日大单增仓占比")
public class OrderClassOfProportionInfo {
    /**
     * 股票代码.市场
     */
    @ApiModelProperty(value = "股票代码.市场", name = "assetId")
    private String assetId;
    /**
     * 股票名称
     */
    @ApiModelProperty(value = "股票名称", name = "assetName")
    private String assetName;
    /**
     * 主买总金额
     */
    @ApiModelProperty(value = "主买总金额", name = "buySum")
    private BigDecimal buySum;
    /**
     * 主卖总金额
     */
    @ApiModelProperty(value = "主卖总金额", name = "saleSum")
    private BigDecimal saleSum;
    /**
     * 非主买、非主卖总金额
     */
    @ApiModelProperty(value = "非主买、非主卖总金额", name = "otherSum")
    private BigDecimal otherSum;
    /**
     * 小单总金额
     */
    @ApiModelProperty(value = "小单总金额", name = "smallTotalSum")
    private BigDecimal smallTotalSum;
    /**
     * 中单总金额
     */
    @ApiModelProperty(value = "中单总金额", name = "middleTotalSum")
    private BigDecimal middleTotalSum;
    /**
     * 大单总金额
     */
    @ApiModelProperty(value = "大单总金额", name = "bigTotalSum")
    private BigDecimal bigTotalSum;
    /**
     * 小单总笔数
     */
    @ApiModelProperty(value = "小单总笔数", name = "smallTotalCount")
    private Integer smallTotalCount;
    /**
     * 中单总笔数
     */
    @ApiModelProperty(value = "中单总笔数", name = "middleTotalCount")
    private Integer middleTotalCount;
    /**
     * 大单总笔数
     */
    @ApiModelProperty(value = "大单总笔数", name = "bigTotalCount")
    private Integer bigTotalCount;
    /**
     * 大单增仓占比
     */
    @ApiModelProperty(value = "大单增仓占比", name = "masukuraProportion")
    private BigDecimal masukuraProportion;
    /**
     * 大单成交占比
     */
    @ApiModelProperty(value = "大单成交占比", name = "tradeProportion")
    private BigDecimal tradeProportion;
    /**
     * 统计开始日期
     */
    @ApiModelProperty(value = "统计开始日期", name = "startTradeDate")
    private String startTradeDate;
    /**
     * 统计截止日期
     */
    @ApiModelProperty(value = "统计截止日期", name = "endTradeDate")
    private String endTradeDate;
}

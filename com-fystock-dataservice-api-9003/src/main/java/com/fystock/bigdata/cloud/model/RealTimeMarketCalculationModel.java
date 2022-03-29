package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 大盘云图实时行情数据分析结果
 *
 * @author He.Yong
 * @since 2021-05-20 10:58:29
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "realTimeMarketCalculationModel", description = "大盘云图实时行情数据分析结果")
public class RealTimeMarketCalculationModel implements Serializable {
    private static final long serialVersionUID = 8655851615225363473L;
    /**
     * 股票代码
     */
    @ApiModelProperty(value = "股票代码", name = "stockCode")
    private String stockCode;

    /**
     * 股票名称
     */
    @ApiModelProperty(value = "股票名称", name = "stockName")
    private String stockName;

    /**
     * 所属一级行业代码
     */
    @ApiModelProperty(value = "所属一级行业代码", name = "induCodeA")
    private String induCodeA;

    /**
     * 所属一级行业代码名称
     */
    @ApiModelProperty(value = "所属一级行业名称", name = "induNameA")
    private String induNameA;

    /**
     * 所属二级行业代码
     */
    @ApiModelProperty(value = "所属二级行业代码", name = "induCodeB")
    private String induCodeB;

    /**
     * 所属二级行业代码名称
     */
    @ApiModelProperty(value = "所属二级行业名称", name = "induNameB")
    private String induNameB;

    /**
     * 单价（港元）
     */
    @ApiModelProperty(value = "单价（港元）", name = "price")
    private String price;

    /**
     * 昨收价（港元）
     */
    @ApiModelProperty(value = "昨收价（港元）", name = "yesPrice")
    private String yesPrice;

    /**
     * 交易市场
     */
    @ApiModelProperty(value = "交易市场", name = "exchangeType")
    private String exchangeType;

    /**
     * 涨跌幅
     */
    @ApiModelProperty(value = "涨跌幅", name = "upAndDown")
    private String upAndDown;

    /**
     * 涨跌状态 (三种状态; 0 - 正常, 1 - 涨停, 2 - 跌停）
     */
    @ApiModelProperty(value = "涨跌状态 (三种状态; 0 - 正常, 1 - 涨停, 2 - 跌停）", name = "status")
    private String status;

    /**
     * 总市值
     */
    @ApiModelProperty(value = "总市值 (万港元)", name = "totalMarketValue")
    private String totalMarketValue;

    /**
     * 流通市值
     */
    @ApiModelProperty(value = "流通市值 (万港元)", name = "flowMarketValue")
    private String flowMarketValue;

    /**
     * 交易日
     */
    @ApiModelProperty(value = "交易日", name = "tradeDate")
    private String tradeDate;

    /**
     * 计算时间
     */
    @ApiModelProperty(value = "计算时间", name = "eventTime")
    private String eventTime;
}

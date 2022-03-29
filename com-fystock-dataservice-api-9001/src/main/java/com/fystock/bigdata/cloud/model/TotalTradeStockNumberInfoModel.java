package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 每日各股票交易次数 可视化数据
 *
 * @author He.Yong
 * @since 2021-03-16 15:56:59
 */
@Data
@ApiModel(value = "totalTradeStockNumberInfoModel", description = "每日各股票交易次数 可视化数据")
public class TotalTradeStockNumberInfoModel extends BaseModel {
    /**
     * 交易日期
     */
    @ApiModelProperty(value = "交易日期", name = "tradeDate")
    private String tradeDate;
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
     * 交易类型
     */
    @ApiModelProperty(value = "交易类型", name = "tradeKind")
    private String tradeKind;
    /**
     * 证券市场
     */
    @ApiModelProperty(value = "证券市场", name = "exchangeType")
    private String exchangeType;
    /**
     * 当日交易次数
     */
    @ApiModelProperty(value = "当日交易次数", name = "dayTimes")
    private Integer dayTimes;
}
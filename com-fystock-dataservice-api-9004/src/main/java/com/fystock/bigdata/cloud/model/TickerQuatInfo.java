package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 成交明细
 *
 * @author He.Yong
 * @since 2021-07-29 16:52:48
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "tickerQuatInfo", description = "逐笔成交明细")
public class TickerQuatInfo {
    /**
     * 大、中、小单标识
     * (-1 小单 1 大单 0 中单)
     */
    @ApiModelProperty(value = "大、中、小单标识(-1 小单 1 大单 0 中单)", name = "orderSize")
    private Integer orderSize;
    /**
     * 主买、主卖标识
     * 0 主卖 1 主买 -1 非主买、非主卖
     */
    @ApiModelProperty(value = "主买、主卖标识(0 主卖 1 主买 -1 非主买、非主卖)", name = "upValue")
    private Integer upValue;
    /**
     * 股票ID
     */
    @ApiModelProperty(value = "股票代码.市场", name = "assetId")
    private String assetId;

    /**
     * 股票交易类型
     */
    @ApiModelProperty(value = "股票交易类型", name = "tickerType")
    private Integer tickerType;

    /**
     * 股票交易价格
     */
    @ApiModelProperty(value = "股票交易价格", name = "tickerTradePrice")
    private Float tickerTradePrice;

    /**
     * 股票交易量
     */
    @ApiModelProperty(value = "股票交易量", name = "tickerTradeQty")
    private Integer tickerTradeQty;

    /**
     * 股票交易方向： 1-主买 2-主卖
     */
    @ApiModelProperty(value = "股票交易方向:1-主买 2-主卖", name = "tickerIsUp")
    private Integer tickerIsUp;

    /**
     * 股票交易时间戳
     */
    @ApiModelProperty(value = "股票交易时间戳", name = "tickerTradeTimestamp")
    private String tickerTradeTimestamp;

    /**
     * 股票交易日期
     */
    @ApiModelProperty(value = "股票交易日期", name = "tickerTradeDate")
    private String tickerTradeDate;

    /**
     * 股票交易时间
     */
    @ApiModelProperty(value = "股票交易时间", name = "tickerTradeTime")
    private String tickerTradeTime;

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
}

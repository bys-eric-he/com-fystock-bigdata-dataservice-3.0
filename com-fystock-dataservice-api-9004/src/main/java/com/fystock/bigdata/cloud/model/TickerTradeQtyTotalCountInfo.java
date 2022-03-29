package com.fystock.bigdata.cloud.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 股票指定交易日总成交量、总成交金额
 *
 * @author He.Yong
 * @since 2021-08-05 15:13:25
 */
@Getter
@Setter
@NoArgsConstructor
public class TickerTradeQtyTotalCountInfo {
    /**
     * 股票ID
     */
    private String assetId;
    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 股票名称
     */
    private String stockName;
    /**
     * 交易日
     */
    private String tradeDate;
    /**
     * 交易总数量
     */
    private Long totalCount;
    /**
     * 交易总金额
     */
    private BigDecimal totalSum;
}

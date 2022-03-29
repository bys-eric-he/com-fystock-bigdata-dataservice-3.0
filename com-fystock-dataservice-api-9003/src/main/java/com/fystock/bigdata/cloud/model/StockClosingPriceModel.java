package com.fystock.bigdata.cloud.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 股票收盘价
 *
 * @author He.Yong
 * @since 2021-08-06
 */
@Getter
@Setter
@NoArgsConstructor
public class StockClosingPriceModel {
    /**
     * 股票ID
     */
    private String assetId;

    /**
     * 交易日期
     */
    private String tradeDate;

    /**
     * 收盘价
     */
    private BigDecimal closingPrice;
}

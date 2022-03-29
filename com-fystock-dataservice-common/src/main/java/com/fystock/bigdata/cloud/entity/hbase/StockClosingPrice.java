package com.fystock.bigdata.cloud.entity.hbase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 股票收盘价
 */
@Getter
@Setter
@NoArgsConstructor
public class StockClosingPrice implements Serializable {
    private static final long serialVersionUID = 2256851615465361125L;

    /**
     * 行键ID
     */
    private String id;

    /**
     * 股票ID
     */
    private String assetId;

    /**
     * 交易日
     */
    private String tradeDate;

    /**
     * 记录发送时间戳
     */
    private String sendTime;

    /**
     * 记录发送序号
     */
    private Long seqNum;

    /**
     * 记录时间
     */
    private String upsertTime;

    /**
     * 收盘价
     */
    private BigDecimal closingPrice;
}

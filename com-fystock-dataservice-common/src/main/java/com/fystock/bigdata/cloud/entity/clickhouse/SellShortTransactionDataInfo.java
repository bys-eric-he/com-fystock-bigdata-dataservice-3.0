package com.fystock.bigdata.cloud.entity.clickhouse;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 即日卖空成交数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellShortTransactionDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 卖空数量
     */
    private Long count;

    /**
     * 卖空金额
     */
    private BigDecimal sum;

    /**
     * 交易日期
     */
    private String tradeDate;

    /**
     * 记录创建时间
     */
    private String createDateTime;
}

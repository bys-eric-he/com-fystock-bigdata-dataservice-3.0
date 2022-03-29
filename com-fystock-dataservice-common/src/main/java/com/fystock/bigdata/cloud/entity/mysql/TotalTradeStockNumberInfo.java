package com.fystock.bigdata.cloud.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 每日各股票交易次数 可视化数据
 *
 * @author He.Yong
 * @since 2021-04-15 13:59:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalTradeStockNumberInfo extends BaseInfo {
    /**
     * 交易日期 。
     */
    private String tradeDate;

    /**
     * 股票代码。
     */
    private String stockCode;

    /**
     * 股票名称。
     */
    private String stockName;

    /**
     * 交易类型。
     */
    private String tradeKind;

    /**
     * 证券市场
     */
    private String exchangeType;

    /**
     * 当日交易次数
     */
    private Integer dayTimes;
}
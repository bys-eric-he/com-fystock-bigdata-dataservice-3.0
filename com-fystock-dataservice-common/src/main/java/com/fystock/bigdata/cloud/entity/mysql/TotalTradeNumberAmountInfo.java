package com.fystock.bigdata.cloud.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 每日交易总笔数、交易总金额数 可视化数据
 *
 * @author He.Yong
 * @since 2021-04-15 13:53:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalTradeNumberAmountInfo extends BaseInfo {
    /**
     * 交易日期 同时添加一个空的ID标识，因为JPA在映射实体是需要一个ID，在这里将trade_date字段作为虚拟主键。
     */
    private String tradeDate;

    /**
     * 交易类型 同时添加一个空的ID标识，因为JPA在映射实体是需要一个ID，在这里将trade_kind与trade_date字段作为复合主键。
     */
    private String tradeKind;

    /**
     * 币种 同时添加一个空的ID标识，因为JPA在映射实体是需要一个ID，在这里将money_type、trade_kind与trade_date字段作为复合主键。
     */
    private String moneyType;

    /**
     * 当日成交笔数
     */
    private BigInteger dayCount;

    /**
     * 当日成交金额
     */
    private BigDecimal dayBalance;
}

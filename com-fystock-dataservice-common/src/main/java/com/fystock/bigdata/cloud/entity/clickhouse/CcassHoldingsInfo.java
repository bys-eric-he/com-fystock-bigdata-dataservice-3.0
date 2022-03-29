package com.fystock.bigdata.cloud.entity.clickhouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * CCASS参与者持仓信息
 *
 * @author He.Yong
 * @since 2021-08-10 17:04:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CcassHoldingsInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 参与者编号
     */
    private Integer participantId;
    /**
     * 参与者编号
     */
    private String participantName;
    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 股票英文名
     */
    private String stockNameEn;
    /**
     * 持股数量
     */
    private BigDecimal stockHolding;
    /**
     * 持股市值
     */
    private BigDecimal stockValue;
    /**
     * 持股比例
     */
    private String stakePercentage;
    /**
     * 持股日期
     */
    private String holdDate;
    /**
     * 总股本
     */
    private BigDecimal totalCapital;
    /**
     * CCASS更新日期
     */
    private String updateDate;
    /**
     * 记录创建时间
     */
    private String createTime;
    /**
     * 记录更新时间
     */
    private String updateTime;
}

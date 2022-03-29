package com.fystock.bigdata.cloud.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 港股交易个股活跃TOP10
 *
 * @author He.Yong
 * @since 2021-06-23 16:38:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BigScreenTradeStockActiveTopInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 当前排名
     */
    private int rank;
    /**
     * 交易日期
     */
    private String date;

    /**
     * 交易数量
     */
    private long businessQty;

    /**
     * 交易总额
     */
    private BigDecimal businessBalance;

    /**
     * 行业名称
     */
    private String induNameA;

    /**
     * 行业编码
     */
    private String induCodeA;

    /**
     * 参与人数
     */
    private int participations;

    /**
     * 股票代码
     */
    private String stkCode;

    /**
     * 股票名称
     */
    private String stkName;

    /**
     * 开窗时间
     */
    private String startTime;

    /**
     * 关窗时间
     */
    private String endTime;
}

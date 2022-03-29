package com.fystock.bigdata.cloud.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Redis缓存中的交易用户数走势、成交笔数走势、交易额走势实时数据
 *
 * @author He.Yong
 * @since 2021-06-17 16:56:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BigScreenTradeAggInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 交易日期
     */
    private String date;

    /**
     * 计算时间：事件时间
     */
    private String endTime;

    /**
     * 开始时间：事件时间
     */
    private String startTime;

    /**
     * 时间段
     */
    private String time;

    /**
     * 买入成交笔数
     */
    private int tradeBuyCount;

    /**
     * 卖出成交笔数
     */
    private int tradeSaleCount;

    /**
     * 交易额
     */
    private double tradeSum;

    /**
     * 交易用户数量
     */
    private int tradeUserCount;

    /**
     * 第几次触发
     */
    private int version;
}

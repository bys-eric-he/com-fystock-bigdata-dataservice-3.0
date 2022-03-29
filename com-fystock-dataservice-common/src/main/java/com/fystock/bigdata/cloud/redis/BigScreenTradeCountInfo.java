package com.fystock.bigdata.cloud.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 港股交易个股数量
 *
 * @author He.Yong
 * @since 2021-06-18 17:12:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BigScreenTradeCountInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 窗口内港股交易个数
     */
    private long tradeCount;
    /**
     * 关窗时间: 事件时间
     */
    private String startTime;
    /**
     * 开窗时间: 事件时间
     */
    private String endTime;
    /**
     * 交易日
     */
    private String date;
    /**
     * 关窗时分秒 HH:mm
     */
    private String time;
}

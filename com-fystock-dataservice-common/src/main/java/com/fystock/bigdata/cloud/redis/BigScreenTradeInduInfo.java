package com.fystock.bigdata.cloud.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 港股行业交易量排行榜
 *
 * @author He.Yong
 * @since 2021-06-18 15:16:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BigScreenTradeInduInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 交易量
     */
    private long tradeCount;
    /**
     * /**
     * 行业名称
     */
    private String induName;
    /**
     * 行业编号
     */
    private String induCode;
    /**
     * 当前排名
     */
    private int rank;
    /**
     * 开窗时间
     */
    private String startTime;
    /**
     * 关窗时间
     */
    private String endTime;
    /**
     * 交易日期
     */
    private String date;
    /**
     * 版本
     */
    private int version;
}

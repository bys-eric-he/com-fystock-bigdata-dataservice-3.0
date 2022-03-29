package com.fystock.bigdata.cloud.entity.hbase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 行情实时计算结果
 *
 * @author He.Yong
 * @since 2021-04-21 13:39:29
 */
@Getter
@Setter
@NoArgsConstructor
public class RealTimeMarketCalculation implements Serializable {
    private static final long serialVersionUID = 8655851615465363474L;

    /**
     * 行键
     */
    private String id;

    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 所属一级行业代码
     */
    private String induCodeA;

    /**
     * 所属一级行业代码名称
     */
    private String induNameA;

    /**
     * 所属二级行业代码
     */
    private String induCodeB;

    /**
     * 所属二级行业代码名称
     */
    private String induNameB;

    /**
     * 单价（港元）
     */
    private String price;

    /**
     * 昨收价（港币）
     */
    private String yesPrice;

    /**
     * 交易市场
     */
    private String exchangeType;

    /**
     * 涨跌幅
     */
    private String upAndDown;

    /**
     * 涨跌状态 (三种状态; 0 - 正常, 1 - 涨停, 2 - 跌停）
     */
    private String status;

    /**
     * 总市值
     */
    private String totalMarketValue;

    /**
     * 流通市值
     */
    private String flowMarketValue;

    /**
     * 交易日
     */
    private String tradeDate;

    /**
     * 计算时间
     */
    private String eventTime;
}

package com.fystock.bigdata.cloud.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 各类用户数占注册总人数比率(转化率) 可视化数据
 *
 * @author He.Yong
 * @since 2021-04-22 09:19:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalCountRatioUserInfo extends BaseInfo {
    /**
     * 用户数类别
     */
    private String type;
    /**
     * 用户总人数
     */
    private BigInteger totalCount;
    /**
     * 注册用户数
     */
    private BigInteger registerTotalCount;
    /**
     * 该类用户数占注册总人数比率(转化率)
     */
    private BigDecimal ratio;
}
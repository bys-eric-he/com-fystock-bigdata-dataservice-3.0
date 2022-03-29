package com.fystock.bigdata.cloud.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 付费用户数据信息【按天统计】
 *
 * @author He.Yong
 * @since 2021-09-13 14:15:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPaymentDInfo extends BaseInfo {
    /**
     * 日增VIP付费用户数
     */
    private int dayVipCount;

    /**
     * 日增VIP付费金额
     */
    private BigDecimal dayVipSum;

    /**
     * VIP付费总用户数
     */
    private int dayVipTotalCount;

    /**
     * VIP付费总金额
     */
    private BigDecimal dayVipTotalSum;

    /**
     * 日增形态猎手付费用户数
     */
    private int dayHunterCount;

    /**
     * 日增形态猎手付费金额
     */
    private BigDecimal dayHunterSum;

    /**
     * 形态猎手付费总用户数
     */
    private int dayHunterTotalCount;

    /**
     * 形态猎手付费总金额
     */
    private BigDecimal dayHunterTotalSum;
}

package com.fystock.bigdata.cloud.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 货币汇率信息
 *
 * @author He.Yong
 * @since 2021-09-10 14:06:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyRateInfo {
    /**
     * 自增ID
     */
    private long id;
    /**
     * 汇率日期
     */
    private Date initDate;
    /**
     * 源货币
     */
    private String fromMoneyType;

    /**
     * 目标货币
     */
    private String toMoneyType;

    /**
     * 正向汇率
     */
    private BigDecimal exchRate;

    /**
     * 反向汇率
     */
    private BigDecimal reverseRate;

    /**
     * 有效时间
     */
    private Date validDate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}

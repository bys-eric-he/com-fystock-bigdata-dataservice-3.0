package com.fystock.bigdata.cloud.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 主买、主卖、非主买、非主卖总金额和总笔数
 */
@Getter
@Setter
@NoArgsConstructor
public class UpOfTotalSumAndCountInfo {
    /**
     * 主买、主卖标识
     * 0 主卖 1 主买 -1 非主买、非主卖
     */
    private Integer upValue;
    /**
     * 总金额
     */
    private BigDecimal totalSum;
    /**
     * 总笔数
     */
    private Double totalCount;
}

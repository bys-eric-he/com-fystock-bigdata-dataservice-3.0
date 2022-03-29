package com.fystock.bigdata.cloud.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 大、中、小单总金额和总笔数
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderSizeOfTotalSumAndCountInfo {
    /**
     * 大、中、小单标识
     * (-1 小单 1 大单 0 中单)
     */
    private Integer orderSize;
    /**
     * 总金额
     */
    private BigDecimal totalSum;
    /**
     * 总笔数
     */
    private Integer totalCount;
}

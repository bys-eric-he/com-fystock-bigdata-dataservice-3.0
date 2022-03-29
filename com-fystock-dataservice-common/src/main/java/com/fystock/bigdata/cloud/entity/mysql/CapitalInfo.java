package com.fystock.bigdata.cloud.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * 股本信息基础数据
 *
 * @author He.Yong
 * @since 2021-04-22 09:19:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CapitalInfo {
    /**
     * 主键自增ID
     */
    private BigInteger id;

    /**
     * 股票代码
     */
    private String symbol;

    /**
     * 总股本
     */
    private BigDecimal totalCapital;

    /**
     * 流通股本
     */
    private BigDecimal flowCapital;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;
}


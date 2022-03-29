package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * CCASS持仓信息变动
 * 港股通持股变动=内地港股通投资者某时刻的持股数-内地港股通投资者基准时刻的持股数
 *
 * @author He.Yong
 * @since 2021-08-23 15:37:25
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "ccassHoldingsChangeInfo", description = "CCASS持仓信息变动")
public class CcassHoldingsChangeInfo {
    /**
     * 股票代码
     */
    @ApiModelProperty(value = "股票代码", name = "stockCode")
    private String stockCode;
    /**
     * 股票英文名
     */
    @ApiModelProperty(value = "股票英文名", name = "stockNameEn")
    private String stockNameEn;
    /**
     * A时刻持股数量
     */
    @ApiModelProperty(value = "A时刻持股数量", name = "stockHoldingA")
    private BigDecimal stockHoldingA;
    /**
     * B时刻持股数量
     */
    @ApiModelProperty(value = "B时刻持股数量", name = "stockHoldingB")
    private BigDecimal stockHoldingB;
    /**
     * A时刻持股日期
     */
    @ApiModelProperty(value = "A时刻持股日期", name = "holdDateA")
    private String holdDateA;
    /**
     * B时刻持股日期
     */
    @ApiModelProperty(value = "B时刻持股日期", name = "holdDateB")
    private String holdDateB;

    /**
     * CCASS参与者持股变动
     */
    @ApiModelProperty(value = "CCASS参与者持股变动", name = "changes")
    private BigDecimal changes;
}

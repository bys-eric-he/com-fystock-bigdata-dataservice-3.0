package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * CCASS持仓信息比例
 * 港股通持股比例=内地港股通投资者持股数/总股本
 *
 * @author He.Yong
 * @since 2021-08-12 11:17:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ccassHoldingsRatioInfo", description = "CCASS持仓信息比例")
public class CcassHoldingsRatioInfo implements Serializable {
    private static final long serialVersionUID = 1L;
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
     * 持股数量
     */
    @ApiModelProperty(value = "持股数量", name = "stockHolding")
    private BigDecimal stockHolding;
    /**
     * 持股市值
     */
    @ApiModelProperty(value = "持股市值", name = "stockValue")
    private BigDecimal stockValue;
    /**
     * 持股比例
     */
    @ApiModelProperty(value = "持股比例", name = "stakePercentage")
    private String stakePercentage;
    /**
     * 持股日期
     */
    @ApiModelProperty(value = "持股日期", name = "holdDate")
    private String holdDate;
    /**
     * 总股本
     */
    @ApiModelProperty(value = "总股本", name = "totalCapital")
    private BigDecimal totalCapital;
    /**
     * CCASS参与者持股比例
     */
    @ApiModelProperty(value = "CCASS参与者持股比例", name = "holdingRatio")
    private BigDecimal holdingRatio;
    /**
     * CCASS更新日期
     */
    @ApiModelProperty(value = "CCASS更新日期", name = "updateDate")
    private String updateDate;
    /**
     * 记录创建时间
     */
    @ApiModelProperty(value = "记录创建时间", name = "createTime")
    private String createTime;
}

package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@NoArgsConstructor
@ApiModel(value = "userPaymentDModel", description = "付费用户数据信息【按天统计】")
public class UserPaymentDModel extends BaseModel {
    /**
     * 日增VIP付费用户数
     */
    @ApiModelProperty(value = "日增VIP付费用户数", name = "dayVipCount")
    private int dayVipCount;

    /**
     * 日增VIP付费金额
     */
    @ApiModelProperty(value = "日增VIP付费金额", name = "dayVipSum")
    private BigDecimal dayVipSum;

    /**
     * 日增VIP付费金额
     */
    @ApiModelProperty(value = "日增VIP付费金额", name = "dayVipTotalCount")
    private int dayVipTotalCount;

    /**
     * VIP付费总金额
     */
    @ApiModelProperty(value = "VIP付费总金额", name = "dayVipTotalSum")
    private BigDecimal dayVipTotalSum;

    /**
     * 日增形态猎手付费用户数
     */
    @ApiModelProperty(value = "日增形态猎手付费用户数", name = "dayHunterCount")
    private int dayHunterCount;

    /**
     * 日增形态猎手付费金额
     */
    @ApiModelProperty(value = "日增形态猎手付费金额", name = "dayHunterSum")
    private BigDecimal dayHunterSum;

    /**
     * 形态猎手付费总用户数
     */
    @ApiModelProperty(value = "形态猎手付费总用户数", name = "dayHunterTotalCount")
    private int dayHunterTotalCount;

    /**
     * 形态猎手付费总金额
     */
    @ApiModelProperty(value = "形态猎手付费总金额", name = "dayHunterTotalSum")
    private BigDecimal dayHunterTotalSum;
}

package com.fystock.bigdata.cloud.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 累计交易总笔数、交易总金额数 可视化数据
 *
 * @author He.Yong
 * @since 2021-03-16 15:21:28
 */
@Data
@NoArgsConstructor
@ApiModel(value = "totalTradeNumberAmountAllInfoModel", description = "累计交易总笔数、交易总金额数 可视化数据")
public class TotalTradeNumberAmountAllInfoModel extends BaseModel {
    /**
     * 交易类型
     */
    @ApiModelProperty(value = "交易类型", name = "tradeKind")
    private String tradeKind;
    /**
     * 币种
     */
    @ApiModelProperty(value = "币种", name = "moneyType")
    private String moneyType;
    /**
     * 累计成交笔数
     */
    @ApiModelProperty(value = "累计成交笔数", name = "count")
    private BigInteger count;
    /**
     * 累计成交金额
     */
    @ApiModelProperty(value = "累计成交金额", name = "balance")
    private BigDecimal balance;
}
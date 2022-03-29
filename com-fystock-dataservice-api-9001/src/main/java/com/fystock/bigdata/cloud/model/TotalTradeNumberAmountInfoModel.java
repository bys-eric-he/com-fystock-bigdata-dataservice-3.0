package com.fystock.bigdata.cloud.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 每日交易总笔数、交易总金额数 可视化数据
 *
 * @author He.Yong
 * @since 2021-03-16 15:28:48
 */
@Data
@NoArgsConstructor
@ApiModel(value = "totalTradeNumberAmountInfoModel", description = "每日交易总笔数、交易总金额数 可视化数据")
public class TotalTradeNumberAmountInfoModel extends BaseModel {
    /**
     * 交易日期
     */
    @ApiModelProperty(value = "交易日期", name = "tradeDate")
    private String tradeDate;
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
     * 当日成交笔数
     */
    @ApiModelProperty(value = "当日成交笔数", name = "dayCount")
    private BigInteger dayCount;
    /**
     * 当日成交金额
     */
    @ApiModelProperty(value = "当日成交金额", name = "dayBalance")
    private BigDecimal dayBalance;
}

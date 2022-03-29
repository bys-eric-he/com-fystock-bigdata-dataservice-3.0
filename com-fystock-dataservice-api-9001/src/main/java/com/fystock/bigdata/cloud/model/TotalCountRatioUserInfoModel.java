package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 各类用户数占注册总人数比率(转化率)
 *
 * @author He.Yong
 * @since 2021-03-16 14:24:28
 */
@Data
@NoArgsConstructor
@ApiModel(value = "totalCountRatioUserInfoModel", description = "各类用户数占注册总人数比率(转化率)")
public class TotalCountRatioUserInfoModel extends BaseModel {
    /**
     * 用户数类别
     */
    @ApiModelProperty(value = "用户数类别", name = "type")
    private String type;
    /**
     * 用户总人数
     */
    @ApiModelProperty(value = "用户总人数", name = "total_count")
    private BigInteger totalCount;
    /**
     * 注册用户数
     */
    @ApiModelProperty(value = "注册用户数", name = "register_total_count")
    private BigInteger registerTotalCount;
    /**
     * 该类用户数占注册总人数比率(转化率)
     */
    @ApiModelProperty(value = "该类用户数占注册总人数比率(转化率)", name = "ratio")
    private BigDecimal ratio;
}
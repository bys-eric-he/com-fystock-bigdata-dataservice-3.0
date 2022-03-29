package com.fystock.bigdata.cloud.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 每日注册人数性别比率
 *
 * @author He.Yong
 * @since 2021-03-16 15:16:28
 */
@Data
@NoArgsConstructor
@ApiModel(value = "genderCountRatioUserInfoModel", description = "每日注册人数性别比率")
public class GenderCountRatioUserInfoModel extends BaseModel {
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", name = "gender")
    private String gender;

    /**
     * 当前性别注册总人数
     */
    @ApiModelProperty(value = "当前性别注册总人数", name = "genderUserCount")
    private BigInteger genderUserCount;

    /**
     * 注册总人数
     */
    @ApiModelProperty(value = "注册总人数", name = "totalUserCount")
    private BigInteger totalUserCount;

    /**
     * 当前性别注册人数占总数比例(%)
     */
    @ApiModelProperty(value = "当前性别注册人数占总数比例(%)", name = "ratio")
    private BigDecimal ratio;
}


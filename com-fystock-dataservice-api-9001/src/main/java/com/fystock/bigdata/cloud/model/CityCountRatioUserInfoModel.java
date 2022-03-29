package com.fystock.bigdata.cloud.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 注册人数按城市分布比率
 *
 * @author He.Yong
 * @since 2021-03-16 13:20:27
 */
@Data
@NoArgsConstructor
@ApiModel(value = "cityCountRatioUserInfoModel", description = "注册人数按城市分布比率")
public class CityCountRatioUserInfoModel extends BaseModel {
    /**
     * 注册城市ID
     */
    @ApiModelProperty(value = "注册城市ID", name = "lastCityId")
    private String lastCityId;
    /**
     * 注册城市名称
     */
    @ApiModelProperty(value = "注册城市名称", name = "city")
    private String city;

    /**
     * 注册城市编码
     */
    @ApiModelProperty(value = "注册城市编码", name = "cityCode")
    private String cityCode;

    /**
     * 注册省份名称
     */
    @ApiModelProperty(value = "注册省份名称", name = "province")
    private String province;

    /**
     * 注册省份编码
     */
    @ApiModelProperty(value = "注册省份编码", name = "provinceCode")
    private String provinceCode;

    /**
     * 当前城市注册总人数
     */
    @ApiModelProperty(value = "当前城市注册总人数", name = "cityUserCount")
    private Integer cityUserCount;
    /**
     * 注册总人数
     */
    @ApiModelProperty(value = "注册总人数", name = "totalUserCount")
    private Integer totalUserCount;
    /**
     * 当前城市注册人数占总数比例(%)
     */
    @ApiModelProperty(value = "当前城市注册人数占总数比例(%)", name = "ratio")
    private BigDecimal ratio;
}

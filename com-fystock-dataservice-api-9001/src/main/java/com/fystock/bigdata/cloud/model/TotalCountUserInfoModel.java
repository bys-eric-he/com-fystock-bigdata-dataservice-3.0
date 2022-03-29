package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * 各类累计用户数统计
 *
 * @author He.Yong
 * @since 2021-03-16 14:58:11
 */
@Data
@NoArgsConstructor
@ApiModel(value = "totalCountUserInfoModel", description = "各类累计用户数统计")
public class TotalCountUserInfoModel extends BaseModel {
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
}

package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 异常用户数【按天统计】
 *
 * @author He.Yong
 * @since 2021-09-13 13:46:28
 */
@Data
@NoArgsConstructor
@ApiModel(value = "userExceptionalCaseDModel", description = "异常用户数【按天统计】")
public class UserExceptionalCaseDModel extends BaseModel {
    /**
     * 异常类型
     */
    @ApiModelProperty(value = "异常类型", name = "type")
    private String type;
    /**
     * 异常用户总人数
     */
    @ApiModelProperty(value = "异常用户总人数", name = "totalCount")
    private long totalCount;
}

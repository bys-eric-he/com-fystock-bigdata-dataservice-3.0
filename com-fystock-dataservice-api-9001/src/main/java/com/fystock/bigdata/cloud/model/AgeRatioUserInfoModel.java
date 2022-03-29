package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 注册用户年龄段占总人数比率数据模型
 *
 * @author He.Yong
 * @since 2021-03-16 12:16:28
 */
@Data
@NoArgsConstructor
@ApiModel(value = "ageRatioUserInfoModel", description = "注册用户年龄段占总人数比率")
public class AgeRatioUserInfoModel extends BaseModel {
    /**
     * 主键ID 添加一个空的ID标识，因为JPA在映射实体是需要一个ID，在这里将age字段作为虚拟主键。
     */
    @ApiModelProperty(value = "年龄", name = "age")
    private String age;

    /**
     * 注册总人数
     */
    @ApiModelProperty(value = "注册总人数", name = "totalCount")
    private Integer totalCount;

    /**
     * 年龄段总人数
     */
    @ApiModelProperty(value = "年龄段总人数", name = "ageCount")
    private Integer ageCount;

    /**
     * 该年龄段占注册总人数比率(%)
     */
    @ApiModelProperty(value = "该年龄段占注册总人数比率(%)", name = "ratio")
    private BigDecimal ratio;

}

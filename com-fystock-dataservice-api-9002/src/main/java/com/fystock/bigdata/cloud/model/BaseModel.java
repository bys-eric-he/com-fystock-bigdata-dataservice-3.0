package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 基础模型对象
 *
 * @author He.Yong
 * @since 2021-02-04 11:21:22
 */
@Data
public class BaseModel {
    @ApiModelProperty(value = "导入日期", name = "importDateTime")
    private String importDateTime;
}


package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据模型基础对象
 *
 * @author He.Yong
 * @since 2021-03-16 13:16:28
 */
@Data
public class BaseModel {
    @ApiModelProperty(value = "导入日期", name = "importDateTime")
    private String importDateTime;
}


package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 每日活跃用户数统计数据模型
 *
 * @author He.Yong
 * @since 2021-03-16 13:16:28
 */
@Data
@NoArgsConstructor
@ApiModel(value = "dayActiveCountUserInfoModel", description = "每日活跃用户数统计")
public class DayActiveCountUserInfoModel extends BaseModel {
    @ApiModelProperty(value = "活跃年月日", name = "currentDay")
    private String currentDay;

    @ApiModelProperty(value = "当日活跃人数", name = "currentDayCount")
    private Integer currentDayCount;
}

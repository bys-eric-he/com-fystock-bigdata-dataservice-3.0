package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 每日在线用户数统计
 *
 * @author He.Yong
 * @since 2021-03-16 14:16:28
 */
@Data
@NoArgsConstructor
@ApiModel(value = "dayOnlineCountUserInfoModel", description = "每日在线用户数统计")
public class DayOnlineCountUserInfoModel extends BaseModel {
    @ApiModelProperty(value = "在线年月日", name = "currentDay")
    private String currentDay;

    @ApiModelProperty(value = "每日在线人数", name = "currentDayCount")
    private Integer currentDayCount;
}
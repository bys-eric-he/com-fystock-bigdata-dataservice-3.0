package com.fystock.bigdata.cloud.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CUBP风险预警账户信息
 *
 * @author He.Yong
 * @since 2021-04-15 11:16:28
 */
@Data
@NoArgsConstructor
@ApiModel(value = "riskWarnInfoModel", description = "风险预警账户信息")
public class RiskWarnInfoModel extends BaseModel {
    /**
     * 风险预警项目编号
     */
    @ApiModelProperty(value = "风险预警项目编号", name = "projectId")
    private int projectId;

    /**
     * 交易账号
     */
    @ApiModelProperty(value = "交易账号", name = "clientId")
    private String clientId;

    /**
     * 规则次数
     */
    @ApiModelProperty(value = "规则次数", name = "count")
    private int count;

    /**
     * 预警发生时间
     */
    @ApiModelProperty(value = "预警发生时间", name = "riskDateTime")
    private String riskDateTime;
}
package com.fystock.bigdata.cloud.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 风控预警信息
 *
 * @author He.Yong
 * @since 2021-04-21 13:39:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskWarnInfo extends BaseInfo {
    /**
     * 风险预警项目编号
     */
    private int projectId;

    /**
     * 交易账号
     */
    private String clientId;

    /**
     * 规则次数
     */
    private int count;

    /**
     * 预警发生时间
     */
    private String riskDateTime;
}

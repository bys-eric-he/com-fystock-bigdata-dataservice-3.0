package com.fystock.bigdata.cloud.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 各类异常用户数【按天统计】
 *
 * @author He.Yong
 * @since 2021-09-13 13:46:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExceptionalCaseDInfo extends BaseInfo {
    /**
     * 异常类型
     */
    private String type;
    /**
     * 异常用户总人数
     */
    private long totalCount;
}

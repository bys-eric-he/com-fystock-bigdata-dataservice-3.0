package com.fystock.bigdata.cloud.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * 各类累计用户数统计 可视化数据
 *
 * @author He.Yong
 * @since 2021-04-15 15:15:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalCountUserInfo extends BaseInfo {
    /**
     * 用户数类别 同时添加一个空的ID标识，因为JPA在映射实体是需要一个ID，在这里将type字段作为虚拟主键。
     */
    private String type;
    /**
     * 用户总人数
     */
    private BigInteger totalCount;
}

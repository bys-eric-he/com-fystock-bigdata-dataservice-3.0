package com.fystock.bigdata.cloud.entity.mysql;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 注册人数按城市分布比率
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityCountRatioUserInfo extends BaseInfo {
    /**
     * 注册城市ID
     */
    private String lastCityId;
    /**
     * 注册城市名称 同时添加一个空的ID标识，因为JPA在映射实体需要一个ID，在这里将city字段作为虚拟主键。
     */
    private String city;
    /**
     * 当前城市注册总人数
     */
    private Integer cityUserCount;
    /**
     * 注册总人数
     */
    private Integer totalUserCount;
    /**
     * 当前城市注册人数占总数比例(%)
     */
    private BigDecimal ratio;
}
package com.fystock.bigdata.cloud.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 每日注册人数性别比率可视化数据
 *
 * @author He.Yong
 * @since 2021-04-15 15:33:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenderCountRatioUserInfo extends BaseInfo {
    /**
     * 性别 同时添加一个空的ID标识，因为JPA在映射实体是需要一个ID，在这里将gender字段作为虚拟主键。
     */
    private String gender;
    /**
     * 当前性别注册总人数
     */
    private BigInteger genderUserCount;

    /**
     * 注册总人数
     */
    private BigInteger totalUserCount;

    /**
     * 当前性别注册人数占总数比例(%)
     */
    private BigDecimal ratio;
}

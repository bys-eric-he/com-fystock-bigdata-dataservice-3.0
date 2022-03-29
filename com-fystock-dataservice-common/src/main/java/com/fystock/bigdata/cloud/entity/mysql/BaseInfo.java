package com.fystock.bigdata.cloud.entity.mysql;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据基类对象
 *
 * @author He.Yong
 * @since 2021-04-18 10:29:19
 */
@Data
public class BaseInfo implements Serializable {
    /**
     * 导入时间
     */
    private String importDateTime;
}

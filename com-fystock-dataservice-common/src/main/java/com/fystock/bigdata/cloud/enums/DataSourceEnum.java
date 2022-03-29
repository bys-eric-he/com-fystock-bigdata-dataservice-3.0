package com.fystock.bigdata.cloud.enums;

/**
 * 枚举类 用于切换数据库
 *
 * @author He.Yong
 * @since 2021-06-03 11:24:25
 */
public enum DataSourceEnum {
    /**
     * BigData Mysql HK_MARKET_DATA 数据库
     */
    bigdataMarketMysql("bigdataMarketMysql"),

    /**
     * 第三方 SqlServer F10 数据库
     */
    f10SqlServer("f10SqlServer"),

    /**
     * SunLine Mysql mktInfo 数据库
     */
    sunlineMktInfoMySql("sunlineMktInfoMySql"),

    /**
     * SunLine Mysql F10 数据库
     */
    sunlineF10Mysql("sunlineF10Mysql"),

    /**
     * CUBP Mysql 数据库
     */
    cubpMysql("cubpMysql");

    private String value;

    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

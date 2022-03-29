package com.fystock.bigdata.cloud.enums;

public enum LoginTypeEnum {
    /**
     * 密码方式
     */
    PASSWORD("password");

    private final String value;

    LoginTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
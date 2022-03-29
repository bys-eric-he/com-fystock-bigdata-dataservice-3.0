package com.fystock.bigdata.cloud.controller;

import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

public interface OAuth2Api {
    /**
     * 登录认证\刷新令牌
     *
     * @param params 参数
     * @return 授权信息
     */
    @RequestLine("POST /oauth/token")
    String oauthToken(@QueryMap Map<String, String> params);
}

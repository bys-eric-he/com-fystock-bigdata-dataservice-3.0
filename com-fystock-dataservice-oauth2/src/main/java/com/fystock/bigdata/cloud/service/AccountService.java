package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.model.LoginInfoModel;
import com.fystock.bigdata.cloud.model.TokenInfoModel;

public interface AccountService {

    /**
     * 登录 获取 Token
     *
     * @param loginInfoModel 登录信息
     * @return 访问 Token
     */
    TokenInfoModel login(LoginInfoModel loginInfoModel);

    /**
     * 获取授权信息
     *
     * @return 授权信息
     */
    Object getPrincipal();

    /**
     * 刷新 token
     *
     * @param refreshToken 上一次登录请求返回的刷新令牌
     * @return accessToken
     */
    TokenInfoModel refreshToken(String refreshToken);
}
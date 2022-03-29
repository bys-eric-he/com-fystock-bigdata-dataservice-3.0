package com.fystock.bigdata.cloud.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.fystock.bigdata.cloud.controller.OAuth2Api;
import com.fystock.bigdata.cloud.exception.BusinessException;
import com.fystock.bigdata.cloud.model.LoginInfoModel;
import com.fystock.bigdata.cloud.model.TokenInfoModel;
import com.fystock.bigdata.cloud.response.ResultCode;
import com.fystock.bigdata.cloud.service.AccountService;
import com.fystock.bigdata.cloud.utils.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 在使用 OAuth2.0 授权协议的时候，最主要的是 client_id\client_secret，这两个字段不应该暴露出来，
 * 不应直接通过前端调用 localhost:7000/oauth/token?password=cubp2021&username=cubp&grant_type=password&client_id=client-1&client_secret=secret
 * 而是把client_id\client_secret这两个字段放在后端服务，向外暴露登录接口;
 * 返回的 refresh_token 不应该直接返回，应该在 access_token 失效之后，根据这个 access_token 获取前一次请求的 refresh_token，再根据 refresh_token 请求 access_token，中间需要多一步骤
 * 刷新令牌 在使用refresh_token，也应该隐藏 client_id\client_secret，不对外暴露，对外提供刷新令牌的接口
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Value("${fyoauth.authorization.client-id}")
    private String OAUTH2_CLIENT;

    @Value("${fyoauth.authorization.client-secret}")
    private String OAUTH2_SECRET;

    @Value("${fyoauth.authorization.login-type}")
    private String LOGIN_TYPE;

    @Resource
    private OAuth2Api oAuth2Api;

    /**
     * 登录获取Token
     * 使用 http 请求框架 （openfeign、okhttp3）,返回授权信息
     * 密码模式传递以下参数,返回授权信息（TokenInfoModel）
     * accountName:cubp
     * password:cubp2021
     * loginType:password
     */
    @Override
    public TokenInfoModel login(LoginInfoModel loginInfoModel) {
        // 通过 HTTP 客户端请求登录接口
        Map<String, String> params = new HashMap<>(16);

        String accountName = loginInfoModel.getAccountName();

        if (StringUtils.isEmpty(accountName)) {
            throw new BusinessException(ResultCode.ACCOUNT_IS_EMPTY);
        }

        String password = loginInfoModel.getPassword();
        if (StringUtils.isEmpty(password)) {
            throw new BusinessException(ResultCode.PASSWORD_IS_EMPTY);
        }

        params.put("username", accountName);
        params.put("password", password);
        params.put("grant_type", LOGIN_TYPE);
        params.put("client_id", OAUTH2_CLIENT);
        params.put("client_secret", OAUTH2_SECRET);

        TokenInfoModel tokenInfoModel = new TokenInfoModel();

        try {
            String jsonString = oAuth2Api.oauthToken(params);
            log.info("-->获取Token, 调用OAuth2.0 POST /oauth/token 接口成功!返回结果：" + jsonString);
            Map<String, Object> authInfo = MapperUtil.json2map(jsonString);
            String accessToken = (String) authInfo.get(OAuth2AccessToken.ACCESS_TOKEN);
            if (StringUtils.isEmpty(accessToken)) {
                Integer code = (Integer) authInfo.get("code");
                String message = (String) authInfo.get("message");
                throw new BusinessException(message, code);
            }

            String refreshToken = (String) authInfo.get(OAuth2AccessToken.REFRESH_TOKEN);
            int expiresIn = (int) authInfo.get(OAuth2AccessToken.EXPIRES_IN);
            String tokenType = (String) authInfo.get(OAuth2AccessToken.TOKEN_TYPE);
            String scope = (String) authInfo.get(OAuth2AccessToken.SCOPE);

            tokenInfoModel.setAccessToken(accessToken);
            tokenInfoModel.setRefreshToken(refreshToken);
            tokenInfoModel.setTokenType(tokenType);
            tokenInfoModel.setExpiresIn(expiresIn);
            tokenInfoModel.setScope(scope);

            return tokenInfoModel;
        } catch (Exception e) {
            log.error("-->调用 /oauth/token get token 失败, {} !", e.getMessage());
            Integer code = ResultCode.AUTH_SERVER_ERROR.getCode();
            String message = ResultCode.AUTH_SERVER_ERROR.getMessage();
            if (e instanceof BusinessException) {
                BusinessException be = (BusinessException) e;
                code = be.getCode();
                message = be.getMessage();
            }
            throw new BusinessException(message, code);
        }
    }

    /**
     * 刷新Token
     *
     * @param refreshToken 上一次登录请求返回的刷新令牌
     * @return
     */
    @Override
    public TokenInfoModel refreshToken(String refreshToken) {

        if (StringUtils.isEmpty(refreshToken)) {
            throw new BusinessException(ResultCode.REFRESH_TOKEN_NOT_EXIST);
        }

        // 通过 HTTP 客户端请求登录接口
        Map<String, String> params = new HashMap<>(16);

        params.put("client_id", OAUTH2_CLIENT);
        params.put("client_secret", OAUTH2_SECRET);
        params.put("grant_type", OAuth2AccessToken.REFRESH_TOKEN);
        params.put("refresh_token", refreshToken);

        String jsonString;
        Map<String, Object> authInfo;
        TokenInfoModel tokenInfoModel = new TokenInfoModel();

        try {
            jsonString = oAuth2Api.oauthToken(params);
            log.info("-->刷新令牌, 调用OAuth2.0 POST /oauth/token 接口成功!返回结果：" + jsonString);
            authInfo = MapperUtil.json2map(jsonString);
        } catch (Exception e) {
            log.error("-->调用 /oauth/token refresh_token 失败, {} !", e.getMessage());
            throw new BusinessException(ResultCode.AUTH_SERVER_ERROR);
        }

        if (StringUtils.isEmpty(jsonString) || ObjectUtils.isEmpty(authInfo)) {
            throw new BusinessException(ResultCode.AUTH_SERVER_ERROR);
        }

        String accessToken = (String) authInfo.get(OAuth2AccessToken.ACCESS_TOKEN);

        if (StringUtils.isEmpty(accessToken)) {
            Integer code = (Integer) authInfo.get("code");
            String message = (String) authInfo.get("message");
            throw new BusinessException(message, code);
        }

        String refreshTokenValue = (String) authInfo.get(OAuth2AccessToken.REFRESH_TOKEN);
        int expiresIn = (int) authInfo.get(OAuth2AccessToken.EXPIRES_IN);
        String tokenType = (String) authInfo.get(OAuth2AccessToken.TOKEN_TYPE);
        String scope = (String) authInfo.get(OAuth2AccessToken.SCOPE);

        tokenInfoModel.setAccessToken(accessToken);
        tokenInfoModel.setRefreshToken(refreshTokenValue);
        tokenInfoModel.setTokenType(tokenType);
        tokenInfoModel.setExpiresIn(expiresIn);
        tokenInfoModel.setScope(scope);

        return tokenInfoModel;
    }

    /**
     * 获取授权信息
     *
     * @return
     */
    @Override
    public Object getPrincipal() {
        Authentication authentication = (Authentication) this.getAuthentication();
        if (authentication != null) {
            return authentication.getPrincipal();
        }
        return null;
    }

    /**
     * 获取登录信息
     *
     * @return Authentication
     */
    private Object getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
package com.fystock.bigdata.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Spring Security Token配置
 *
 * @author He.Yong
 * @since 2021-04-21 15:28:04
 */
@Configuration
public class TokenConfig {

    /**
     * 密钥
     */
    final private String SIGNING_KEY = "fystock-bigdata-2021";

    /**
     * 生成令牌存储方式
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * 对称秘钥，资源服务器使用该秘钥来验证
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }
}

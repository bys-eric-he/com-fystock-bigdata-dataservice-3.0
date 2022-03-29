package com.fystock.bigdata.cloud.config;

import com.fystock.bigdata.cloud.handler.AuthAccessDeniedHandler;
import com.fystock.bigdata.cloud.handler.AuthenticationEntryPointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /**
     * resourceId 用于分配给可授予的clientId
     */
    @Value("${fyoauth.resource-id}")
    public String RESOURCE_ID;

    @Resource
    private TokenStore tokenStore;

    @Autowired
    private AuthAccessDeniedHandler authAccessDeniedHandler;

    @Autowired
    private AuthenticationEntryPointHandler authenticationEntryPointHandler;

    /**
     * 添加客户端资源ID
     *
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID)
                //自定义资源访问认证异常，没有token,或token错误，使用AuthenticationEntryPointHandler处理
                .accessDeniedHandler(authAccessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPointHandler) //认证异常流程处理返回
                .tokenStore(tokenStore) //token的存储方式
                .stateless(true); //stateless  标记以指示在这些资源上仅允许基于令牌的身份验证
    }

    /**
     * 在ResourceServerConfigurerAdapter配置需要token验证的资源
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 定义 "/api/token/v1/**" 可任意访问
                .antMatchers("/api/token/v1/**")
                .permitAll()
                .antMatchers("/**")
                //定义 "/**" 所有的请求都需要拥有以下权限才可访问
                .access("#oauth2.hasAnyScope('ROLE_ADMIN','ROLE_USER','ROLE_API','ROLE_FYSTOCK')")
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
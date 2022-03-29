package com.fystock.bigdata.cloud.config;

import com.fystock.bigdata.cloud.handler.WebResponseExceptionTranslatorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Collections;

/**
 * 认证服务器,通过/oauth/token 登录获取认证token
 *
 * @author He.Yong
 * @since 2021-03-15 15:32:00
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * JWT令牌存储方案
     */
    @Resource
    private TokenStore tokenStore;
    /**
     * jks公钥
     */
    @Resource
    private JwtAccessTokenConverter accessTokenConverter;
    /**
     * 将客户端client id secret这些信息存储到数据库
     */
    @Resource
    private ClientDetailsService clientDetailsService;
    /**
     * 授权码
     */
    @Resource
    private AuthorizationCodeServices authorizationCodeServices;
    /**
     * 认证管理器
     */
    @Resource
    private AuthenticationManager authenticationManager;
    /**
     * 加密
     */
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 登录认证异常处理配置
     */
    @Autowired
    private WebResponseExceptionTranslatorHandler webResponseExceptionTranslatorHandler;

    /**
     * accessToken 过期时间 2小时  = 60 * 60 * 2
     */
    @Value("${fyoauth.token.accesstoken-validity-second}")
    private int ACCESSTOKEN_VALIDITY_SECOND;
    /**
     * token刷新有效期 7天 = 60 * 60 * 24 * 7
     */
    @Value("${fyoauth.token.refreshtoken-validity-second}")
    private int REFRESHTOKEN_VALIDITY_SECOND;

    /**
     * 从数据库读取客户端信息
     *
     * @param dataSource
     * @return
     */
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    /**
     * 设置授权码存储方式:InMemoryAuthorizationCodeServices()为内存形式
     *
     * @param dataSource
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 生成令牌
     *
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        //为解决获取token并发问题
        DefaultTokenServices service = new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService); //配置加载用户信息的服务
        service.setSupportRefreshToken(true);
        service.setTokenStore(tokenStore);
        //令牌增强，使用JWT
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);

        //如果oauth_client_details中有配置，则以access_token_validity 和 refresh_token_validity 为准
        service.setAccessTokenValiditySeconds(ACCESSTOKEN_VALIDITY_SECOND); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(REFRESHTOKEN_VALIDITY_SECOND); // 刷新令牌默认有效期7天
        return service;
    }

    /**
     * 客户端配置
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        //jdbc数据库模式
        clients.withClientDetails(clientDetailsService);

        /*
        //内存模式
        clients.inMemory().withClient("client-1")
                .secret("secret")
                .resourceIds("resource-1")
                .authorizedGrantTypes("password")
                .authorities("ROLE_ADMIN,ROLE_USER,ROLE_FYSTOCK")
                .scopes("read", "write")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(50000);*/
    }

    /**
     * 令牌访问端点,刷新令牌时需要的认证管理和用户信息来源
     *
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager) //配置授权管理认证对象
                .authorizationCodeServices(authorizationCodeServices)   //授权码模式
                //自定义 /oauth/token 异常处理
                //由于spring security的认证原理是通过注册到容器tomcat的filter链上,使得认证异常不能通过DispatcherServlet
                //所以@ExceptionHandler处理不到, 自定义异常需要配置两处
                .exceptionTranslator(webResponseExceptionTranslatorHandler)
                .tokenServices(tokenService())  //令牌管理服务,生成令牌
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); //允许请求token验证的http方式
    }

    /**
     * 令牌访问端点安全策略
     *
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")//oauth/token_key 请求公开
                .checkTokenAccess("permitAll()")//oauth/check_token 请求公开访问
                .allowFormAuthenticationForClients(); //允许表单认证
    }
}
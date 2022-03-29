package com.fystock.bigdata.cloud.config;

import com.fystock.bigdata.cloud.properties.IgnoreUrlsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 在WebSecurityConfigurerAdapter不拦截oauth要开放的资源
 *
 * @author He.Yong
 * @since 2021-03-23 18:41:08
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IgnoreUrlsProperties ignoreUrlsProperties;

    /**
     * 加密方式
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //定义不需要保护的URL
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();

        //这里表示不需要权限校验
        ignoreUrlsProperties.getUrls().forEach(url -> registry.antMatchers(url).permitAll());

        // 禁用csrf模式, 由于使用的是JWT,我们这里不需要csrf;
        http.csrf().disable().authorizeRequests()
                //定义/auth/*路径的访问权限
                .antMatchers("/auth/*","/api/token/v1/*")
                .permitAll() // 路径为/auth/* 请求可以任意访问
                //定义其它路径的访问权限
                .anyRequest()
                .authenticated();//  除以上情况之外的请求必须认证通过
                //.and().cors();//允许跨域访问(在网关层配置了跨域,这里就不再设置)
    }

    /**
     * Spring Security 两种资源放行策略
     * 通过WebSecurity这种方法不走Spring Security过虑器链，通常静态资源可以使用这种方式过虑放行，因为这些资源不需要权限就可以访问。
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //权限控制需要忽略所有静态资源，不然登录页面未登录状态无法加载css等静态资源
        ignoreUrlsProperties.getResources().forEach(resource -> web.ignoring().antMatchers(resource));
    }
}

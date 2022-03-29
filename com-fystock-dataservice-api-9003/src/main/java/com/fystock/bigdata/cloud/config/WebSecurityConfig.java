package com.fystock.bigdata.cloud.config;

import com.fystock.bigdata.cloud.properties.IgnoreUrlsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * Spring security 核心配置, 对用户提供安全的校验,确保用户能够访问必要的资源
 *
 * @author He.Yong
 * @since 2021-02-04 19:32:11
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IgnoreUrlsProperties ignoreUrlsProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定义不需要保护的URL
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();

        //这里表示不需要权限校验
        ignoreUrlsProperties.getUrls().forEach(url -> registry.antMatchers(url).permitAll());

        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/**")
                .authenticated()//所有/api/**的请求必须认证通过
                .anyRequest()
                .permitAll(); //除了/api/**，其它的请求可以访问
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

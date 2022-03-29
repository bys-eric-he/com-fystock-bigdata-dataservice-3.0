package com.fystock.bigdata.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Swagger-UI配置
 *
 * @author He.Yong
 * @since 2021年02月04日20:35:22
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * Swagger组件注册
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(LocalDateTime.class, Date.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("富元环球数据服务-OAuth2.0认证中心API 1.0")
                .description("提供大数据接口身份统一认证服务")
                .termsOfServiceUrl("http://localhost:9999/swagger-ui.html")
                .contact(new Contact("何涌", "", "heyong@fuyuanhk.com"))
                .version("1.0")
                .build();
    }
}
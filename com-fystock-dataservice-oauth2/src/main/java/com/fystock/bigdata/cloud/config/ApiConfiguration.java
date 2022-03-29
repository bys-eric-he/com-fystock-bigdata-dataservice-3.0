package com.fystock.bigdata.cloud.config;

import com.fystock.bigdata.cloud.controller.OAuth2Api;
import com.fystock.bigdata.cloud.feign.DefaultErrorDecoder;
import feign.Feign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {

    @Value("${server.port}")
    private String serverPort;

    @Bean
    public OAuth2Api oAuth2Api() {
        return Feign.builder()
                .errorDecoder(new DefaultErrorDecoder())
                .target(OAuth2Api.class, "http://localhost:" + serverPort);
    }
}
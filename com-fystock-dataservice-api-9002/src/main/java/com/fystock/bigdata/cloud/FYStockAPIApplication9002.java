package com.fystock.bigdata.cloud;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.fystock.bigdata.cloud.config.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableDubbo
public class FYStockAPIApplication9002 {

    public static void main(String[] args) {
        SpringApplication.run(FYStockAPIApplication9002.class, args);
        log.info("------->端口：{}, 服务启动完成!", SpringContextHolder.getBean(ServerConfig.class).getServerPort());
        log.info("------->访问：{}", SpringContextHolder.getBean(ServerConfig.class).getUrl());
    }

    /**
     * Spring Cloud Alibaba Sentinel 注解支持的配置Bean
     *
     * @return
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}

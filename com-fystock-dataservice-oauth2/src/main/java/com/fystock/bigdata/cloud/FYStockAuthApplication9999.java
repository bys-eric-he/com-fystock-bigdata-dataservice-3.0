package com.fystock.bigdata.cloud;

import com.fystock.bigdata.cloud.config.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class FYStockAuthApplication9999 {

    public static void main(String[] args) {
        SpringApplication.run(FYStockAuthApplication9999.class, args);
        PasswordEncoder passwordEncoder = SpringContextHolder.getBean(PasswordEncoder.class);
        String password = "cubp2021";
        log.info("------->端口：{}, 服务启动完成!", SpringContextHolder.getBean(ServerConfig.class).getServerPort());
        log.info("------->访问：{}", SpringContextHolder.getBean(ServerConfig.class).getUrl());
        log.info("-->明文：{},BCryptPasswordEncoder密文：{}", password, passwordEncoder.encode(password));
    }
}

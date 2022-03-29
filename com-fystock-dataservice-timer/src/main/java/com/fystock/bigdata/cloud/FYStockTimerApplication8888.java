package com.fystock.bigdata.cloud;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@EnableDubbo
@SpringBootApplication
public class FYStockTimerApplication8888 {
    public static void main(String[] args) {
        SpringApplication.run(FYStockTimerApplication8888.class, args);
        log.info("------->定时任务服务启动完成<--------");
    }
}

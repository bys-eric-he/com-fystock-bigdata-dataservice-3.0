package com.fystock.bigdata.cloud.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于配置不需要保护的资源路径
 *
 * @author He.Yong
 * @since 2021-06-11 15:16:29
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsProperties {
    /**
     * 接口资源
     */
    private List<String> urls = new ArrayList<>();

    /**
     * 静态资源
     */
    private List<String> resources = new ArrayList<>();
}
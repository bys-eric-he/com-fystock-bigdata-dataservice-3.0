package com.fystock.bigdata.cloud.controller;

import cn.hutool.json.JSONUtil;
import com.fystock.bigdata.cloud.response.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 熔断降级处理器
 *
 * @author He.Yong
 * @since 2021-03-17 18:12:00
 */
@RestController
public class FallbackController {

    @RequestMapping("/fallback")
    public Object fallback() {
        return JSONUtil.toJsonStr(CommonResult.failed("请求的服务不可用,服务熔断策略已经终断请求并返回!"));
    }
}
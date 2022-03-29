package com.fystock.bigdata.cloud.exception;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fystock.bigdata.cloud.response.CommonResult;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class CustomOAuthExceptionSerializer  extends StdSerializer<CustomOAuthException> {

    public CustomOAuthExceptionSerializer() {
        super(CustomOAuthException.class);
    }

    @Override
    public void serialize(CustomOAuthException value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        log.warn("------认证失败------");
        String body;
        if (value.getMessage().contains("Token has expired")) {
            body = JSONUtil.toJsonStr(CommonResult.authenticationExpired(value.getMessage()));
        } else if (value.getMessage().contains("用户名或密码错误")) {
            body = JSONUtil.toJsonStr(CommonResult.authenticationFailed(value.getMessage()));
        } else if (value.getMessage().contains("Cannot convert access token to JSON")) {
            body = JSONUtil.toJsonStr(CommonResult.validateFailed(value.getMessage()));
        } else {
            body = JSONUtil.toJsonStr(CommonResult.failed(value.getMessage()));
        }
        log.warn(body);
        jsonGenerator.writeRawValue(body);
    }
}
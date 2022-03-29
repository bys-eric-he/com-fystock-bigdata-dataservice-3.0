package com.fystock.bigdata.cloud.feign;

import com.fystock.bigdata.cloud.exception.BusinessException;
import com.fystock.bigdata.cloud.response.ResultCode;
import com.fystock.bigdata.cloud.utils.MapperUtil;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class DefaultErrorDecoder implements ErrorDecoder {
    @Override public Exception decode(String s, Response response) {
        try {
            InputStream inputStream = response.body().asInputStream();
            String content = IOUtils.toString(inputStream, String.valueOf(StandardCharsets.UTF_8));
            log.error(content);
            Map<String, Object> result = MapperUtil.json2map(content);
            if (null == content || result.isEmpty()) {
                return new BusinessException(ResultCode.AUTH_SERVER_ERROR);
            }
            String message = (String) result.get("message");
            Integer code = (Integer) result.get("code");
            return new BusinessException(message, code);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return new BusinessException(ResultCode.AUTH_SERVER_ERROR);
        }
    }
}

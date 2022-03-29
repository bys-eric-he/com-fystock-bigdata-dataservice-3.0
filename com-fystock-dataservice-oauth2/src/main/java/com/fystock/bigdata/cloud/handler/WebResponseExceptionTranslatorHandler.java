package com.fystock.bigdata.cloud.handler;

import com.fystock.bigdata.cloud.exception.CustomOAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * 对于登录认证异常处理配置，然后是处理类，通过WebResponseExceptionTranslator转为自定义的OAuth2Exception
 * 然后使用jackson定制化类的json序列化，达到自定义异常信息的目的
 *
 * @author He.Yong
 * @since 2021-03-15 15:43:04
 */
@Component
public class WebResponseExceptionTranslatorHandler implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception exception) throws Exception {
        if (exception instanceof OAuth2Exception) {
            OAuth2Exception oAuth2Exception = (OAuth2Exception) exception;
            return ResponseEntity
                    .status(oAuth2Exception.getHttpErrorCode())
                    .body(new CustomOAuthException(oAuth2Exception.getMessage()));
        } else if (exception instanceof AuthenticationException) {
            AuthenticationException authenticationException = (AuthenticationException) exception;
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new CustomOAuthException(authenticationException.getMessage()));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CustomOAuthException(exception.getMessage()));
    }
}
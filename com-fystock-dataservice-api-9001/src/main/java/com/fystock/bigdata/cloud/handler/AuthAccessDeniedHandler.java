package com.fystock.bigdata.cloud.handler;

import cn.hutool.json.JSONUtil;
import com.fystock.bigdata.cloud.response.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义没有权限访问时的返回结果
 * 令牌不能访问该资源 （403）异常等
 *
 * @author He.Yong
 * @since 2021-03-11 17:57:39
 */
@Slf4j
@Component
public class AuthAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("------没有权限访问------");
        response.setStatus(HttpStatus.OK.value());
        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript;charset=utf-8");
        String body = JSONUtil.toJsonStr(CommonResult.forbidden(accessDeniedException.getMessage()));
        response.getWriter().write(body);
    }
}
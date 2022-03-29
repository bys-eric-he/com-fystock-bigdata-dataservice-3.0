package com.fystock.bigdata.cloud.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fystock.bigdata.cloud.response.ResultCode;
import org.springframework.security.core.AuthenticationException;

public class BusinessException extends AuthenticationException {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private Integer code;

    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(String message, Integer code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = String.format("%s %s", message, cause.getMessage());
    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.message = resultCode.getMessage();
        this.code = resultCode.getCode();
    }

    public BusinessException(ResultCode resultCode, Object... args) {
        super(resultCode.getMessage());
        String message = resultCode.getMessage();
        try {
            message =
                    String.format("%s %s", resultCode.getMessage(), OBJECT_MAPPER.writeValueAsString(args));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.message = message;
        this.code = resultCode.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

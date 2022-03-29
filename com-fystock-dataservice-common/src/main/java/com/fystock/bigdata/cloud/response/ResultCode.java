package com.fystock.bigdata.cloud.response;

/**
 * 枚举常用Response操作码
 *
 * @author He.Yong
 * @since 2021-03-11 18:33:25
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功!"),

    FAILED(500, "操作失败!"),
    AUTHENTICATION_FAILED(50001, "获取token失败!"),
    AUTH_SERVER_ERROR(50002, "授权服务异常!"),
    LOGIN_METHOD_IS_EMPTY(50003, "登录方式不能为空!"),
    LOGIN_METHOD_IS_INVALID(50004, "登录方式只支持password模式!"),

    UNAUTHORIZED(40001, "身份认证失败!"),
    VALIDATE_FAILED(40002, "Token参数校验失败,请检查Token是否正确!"),
    FORBIDDEN(40003, "无权限请求,请检测当前用户身份信息!"),
    ACCOUNT_IS_EMPTY(40004, "账号不能为空!"),
    PASSWORD_IS_EMPTY(40005, "密码不能为空!"),

    REFRESH_TOKEN_NOT_EXIST(60001, "刷新令牌值不能为空!"),
    PARAM_IS_INVALID(60002, "参数校验失败!"),
    PARAMS_REQUEST_VALIDATE_FAIL(60003, "请求参数错误!");

    /**
     * 消息码
     */
    private Integer code;
    /**
     * 消息体
     */
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

package com.fystock.bigdata.cloud.exception;


import com.alibaba.fastjson.JSONObject;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局异常处理。
 * 1、@ControllerAdvice 表示为应用下所有的控制器给出建议，类中提供 @ExceptionHandler 异常处理方法，
 * 所以组合起来就是为整个应用下的所有控制器给出全局异常处理建议。
 * 2、如同 @RestController 注解组合了 @Controller 与 @ResponseBody 注解一样，@RestControllerAdvice
 * 也组合了 @ControllerAdvice 与 @ResponseBody 注解
 * 3、所以 @ControllerAdvice 中的  @ExceptionHandler 方法返回默认是做页面跳转，加上 @ResponseBody 才会直接返回给页面
 * 而 @RestControllerAdvice 中的 @ExceptionHandler 方法则默认是返回给页面。
 *
 * @author He.Yong
 * @since 2021-03-11 14:28:39
 */
@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    /**
     * # @Validated @Valid 方法参数无效异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Object handleMethodArgumentNotValidException(BindException ex) {
        log.error("----BindException 全局异常处理【@Validated @Valid参数校验错误】-----");
        log.error("异常信息：{}", ex.getMessage());
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder(bindingResult.getFieldErrors().size() * 16);
        for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
            if (i > 0) {
                errorMessage.append(",");
            }

            FieldError fieldError = bindingResult.getFieldErrors().get(i);
            errorMessage.append("参数->");
            errorMessage.append(fieldError.getField());
            errorMessage.append(":");
            errorMessage.append(fieldError.getDefaultMessage());
        }
        return CommonResult.failed(ResultCode.PARAM_IS_INVALID, errorMessage.toString());
    }

    /**
     * # @NotBlank @NotNull @NotEmpty方法参数无效异常捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Object handleMethodArgumentNotValidException(ConstraintViolationException ex) {
        log.error("----ConstraintViolationException 全局异常处理【@NotBlank @NotNull @NotEmpty方法参数校验错误】-----");

        List<String> msgList = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            msgList.add(constraintViolation.getMessage());
        }

        log.error("异常信息：{}", JSONObject.toJSONString(msgList));
        String messages = StringUtils.join(msgList.toArray(), ";");
        return CommonResult.failed(ResultCode.PARAM_IS_INVALID, messages);
    }

    /**
     * 权限异常拦截捕获
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public CommonResult<Object> accessDeniedException(AccessDeniedException ex) {
        // 1、使用日志框架记录日志信息
        log.error("----AccessDeniedException 全局异常处理【权限异常】-----");
        log.error("异常信息：" + ex.getMessage(), ex);
        // 2、使用统一的数据对象返回给页面，比如状态码，错误消息，数据对象等
        return CommonResult.failed(ResultCode.FORBIDDEN, ex.getMessage());
    }

    /**
     * ExceptionHandler：异常处理注解，只有一个 value 属性，用于捕获特定的异常类型，为空时表示捕获所有异常
     * ExceptionHandler 异常处理方法的返回值是做页面跳转还是直接返回给页面，取决于所在的类使用的是 ControllerAdvice 注解还是 RestControllerAdvice 注解，
     * ControllerAdvice 时 ExceptionHandler 方法的返回值默认是页面跳转（除非 ExceptionHandler 方法再加上 @ResponseBody 注解，此时就会直接返回给页面）；
     * RestControllerAdvice 时 @ExceptionHandler 方法返回值默认是直接返回给页面。
     *
     * @param ex ：目标方法实际抛出的异常对象
     * @return ：原 @RequestMapping 方法的返回值不会再走，而是会使用 @ExceptionHandler 方法的返回值，
     * 比如页面跳转，或者将数据返回给页面等等，而且两者的返回值类型可以不一致，比如原来是 Map,现在是 String。
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult<Object> exceptionHandler(Exception ex) {
        // 1、使用日志框架记录日志信息
        log.error("----Exception 全局异常处理【系统异常】-----");
        log.error("异常信息：" + ex.getMessage(), ex);

        // 2、使用统一的数据对象返回给页面，比如状态码，错误消息，数据对象等
        return CommonResult.failed(ResultCode.FAILED, ex.getMessage());
    }
}
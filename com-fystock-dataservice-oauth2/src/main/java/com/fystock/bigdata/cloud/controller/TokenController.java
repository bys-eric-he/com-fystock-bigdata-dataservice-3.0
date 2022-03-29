package com.fystock.bigdata.cloud.controller;

import com.fystock.bigdata.cloud.model.LoginInfoModel;
import com.fystock.bigdata.cloud.model.TokenInfoModel;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Api(value = "/api/token/v1", tags = "获取Token接口封装")
@RestController
@Validated
@RequestMapping("/api/token/v1")
public class TokenController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private AccountService accountService;

    @ApiOperation("根据登录信息获取Token")
    @PostMapping(value = "/login")
    public CommonResult<TokenInfoModel> login(@RequestBody @Valid LoginInfoModel loginInfoModel) {
        return CommonResult.success(accountService.login(loginInfoModel), "获取Token成功, 处理服务端口：" + serverPort);
    }

    @ApiOperation("根据过期Token的Refresh Token信息刷新Token")
    @PostMapping(value = "/refresh")
    public CommonResult<TokenInfoModel> refreshToken(@RequestParam
                                                     @NotBlank(message = "刷新令牌信息不能为空字符串!")
                                                     @NotNull(message = "刷新令牌信息不能为空!") String refreshToken) {
        return CommonResult.success(accountService.refreshToken(refreshToken), "刷新Token成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取授权的用户信息
     *
     * @return 授权信息
     */
    @ApiOperation("获取授权的用户信息")
    @GetMapping("/current")
    public CommonResult<Object> getPrincipal() {
        return CommonResult.success(accountService.getPrincipal(), "获取授权的用户信息成功, 处理服务端口：" + serverPort);
    }
}

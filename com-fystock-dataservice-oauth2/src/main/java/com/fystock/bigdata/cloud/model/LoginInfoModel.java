package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 获取Token数据认证信息
 */
@Data
@NoArgsConstructor
@ApiModel(value = "loginInfoModel", description = "登录身份认证信息")
public class LoginInfoModel {

    /**
     * 账号
     */
    @NotNull(message = "账号不能为空!")
    @NotBlank(message ="账号不能为空字符串!")
    @ApiModelProperty(value = "账号", name = "accountName")
    private String accountName;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空!")
    @NotBlank(message ="密码不能为空字符串!")
    @ApiModelProperty(value = "密码", name = "password")
    private String password;

}

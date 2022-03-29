package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "userModel", description = "系统用户信息")
public class UserModel {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", name = "id")
    private Integer id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "userName")
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "passWord")
    private String passWord;
    /**
     * 角色
     */
    @ApiModelProperty(value = "角色", name = "role")
    private String role;
}

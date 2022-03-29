package com.fystock.bigdata.cloud.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "tokenInfoModel", description = "Token身份认证信息")
public class TokenInfoModel {
    /**
     * Token令牌信息
     */
    @ApiModelProperty(value = "Token", name = "accessToken")
    private String accessToken;

    /**
     * 刷新令牌
     */
    @ApiModelProperty(value = "刷新Token", name = "refreshToken")
    private String refreshToken;

    /**
     * Token类型
     */
    @ApiModelProperty(value = "Token类型", name = "tokenType")
    private String tokenType;

    /**
     * 过期时间(秒)
     */
    @ApiModelProperty(value = "过期时间(秒)", name = "expiresIn")
    private int expiresIn;

    /**
     * 身份权限范围
     */
    @ApiModelProperty(value = "身份权限范围", name = "scope")
    private String scope;
}

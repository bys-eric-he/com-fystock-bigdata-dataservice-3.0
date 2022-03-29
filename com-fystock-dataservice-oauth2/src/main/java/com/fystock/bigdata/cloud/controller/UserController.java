package com.fystock.bigdata.cloud.controller;

import cn.hutool.json.JSONObject;
import com.fystock.bigdata.cloud.mapper.UserMapper;
import com.fystock.bigdata.cloud.entity.mysql.User;
import com.fystock.bigdata.cloud.mapping.UserMapping;
import com.fystock.bigdata.cloud.model.UserModel;
import com.fystock.bigdata.cloud.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/api/user/v1", tags = "系统用户管理")
@Validated
@RestController
@RequestMapping("/api/user/v1")
public class UserController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    public UserMapper userMapper;

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId
     * @return
     */
    @ApiOperation("根据用户ID获取用户信息")
    @GetMapping("/getById/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CommonResult<UserModel> getById(@PathVariable
                                           @NotBlank(message = "用户ID不能为空字符串!")
                                           @NotNull(message = "用户ID不能为空!") Integer userId) {
        User user = userMapper.selectByUserId(userId);
        return CommonResult.success(UserMapping.toModel(user), "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    @ApiOperation("根据用户名获取用户信息")
    @GetMapping("/getByName/{userName}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CommonResult<UserModel> getByName(@PathVariable
                                             @NotBlank(message = "用户名不能为空字符串!")
                                             @NotNull(message = "用户名不能为空!") String userName) {
        User user = userMapper.selectByUserName(userName);
        return CommonResult.success(UserMapping.toModel(user), "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @ApiOperation("获取所有用户信息")
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CommonResult<List<UserModel>> getAll() {
        List<User> users = userMapper.getAll();

        List<UserModel> results = new ArrayList<>();
        users.forEach(o -> results.add(UserMapping.toModel(o)));
        return CommonResult.success(results, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 获取授权的用户信息
     *
     * @return 授权信息
     */
    @ApiOperation("获取授权的用户信息")
    @GetMapping("/current/get")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CommonResult<JSONObject> user() {
        //从Header中获取用户信息
        /*
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");*/


        //oauth2通过SecurityContextHolder.getContext().getAuthentication().getPrincipal()却只能拿到当前用户的用户名。
        Object username = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (ObjectUtils.isNotEmpty(username)) {
            User user = userMapper.selectByUserName((String) username);
            JSONObject userJsonObject = new JSONObject(user);
            return CommonResult.success(userJsonObject, "查询数据成功, 处理服务端口：" + serverPort);
        } else {
            return CommonResult.success(null, "查询数据成功, 处理服务端口：" + serverPort);
        }
    }
}
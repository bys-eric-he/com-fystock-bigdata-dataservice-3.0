package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.mapper.UserMapper;
import com.fystock.bigdata.cloud.entity.mysql.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 加载用户信息
 *
 * @author He.Yong
 * @since 2021-03-15 14:56:00
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //获取本地用户
        User user = userMapper.selectByUserName(userName);
        if (ObjectUtils.isNotEmpty(user)) {
            //返回oauth2的用户
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassWord(),
                    // 将用户表中配置的role角色信息放入authorities 中
                    // 后续@PreAuthorize("hasAnyAuthority('FYSTOCK')")就会判断用户的authorities 中是否有该角色, 有则可以访问
                    AuthorityUtils.createAuthorityList(user.getRole()));
        } else {
            throw new UsernameNotFoundException("--->用户[" + userName + "]不存在!");
        }
    }
}
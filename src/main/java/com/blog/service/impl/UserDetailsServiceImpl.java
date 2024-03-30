package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.exception.APIException;
import com.blog.mapper.UserMapper;
import com.blog.model.dto.security.UserDetailsDTO;
import com.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (StringUtils.isBlank(s)) {
            throw new APIException("用户名不能为空");
        }
        // 查询用户信息
        // 默认使用的 PasswordEncoder 用户的密码如果是明文需要在明文密码前加上 {noop}
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, s);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new APIException("用户名或密码错误");
        }
        // 查询对应的权限信息
        // 默认使用的 Permission 查询对应的权限信息
//        List<String> roles = userMapper.getRoles(user.getId());

        // 把数据封装成UserDetails返回
        return UserDetailsDTO.builder().roles(new ArrayList<>()).user(user).build();
    }
}
package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.exception.APIException;
import com.blog.mapper.RoleMapper;
import com.blog.mapper.UserMapper;
import com.blog.model.dto.security.UserDetailsDTO;
import com.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (StringUtils.isBlank(s)) {
            throw new APIException("用户名不能为空");
        }
        // 查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, s);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new APIException("用户名或密码错误");
        }
        // 查询对应的角色信息
        List<String> roles = roleMapper.getRoles(user.getId());

        return UserDetailsDTO.builder().roles(roles).user(user).build();
    }
}
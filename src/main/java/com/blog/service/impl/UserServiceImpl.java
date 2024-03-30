package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.enums.user.UserEnum;
import com.blog.exception.APIException;
import com.blog.mapper.UserMapper;
import com.blog.model.dto.user.RegisterDTO;
import com.blog.pojo.User;
import com.blog.service.UserService;
import com.blog.utils.Constant;
import com.blog.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = this.getOne(queryWrapper);

        if (user != null) {
            throw new APIException(Constant.USERNAME_ALREADY_ERROR);
        }

        user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setNickname(UUID.randomUUID().toString().replaceAll("-", ""));
        user.setIsDisable(UserEnum.NORMAL.getCode());
        user.setLastLoginTime(LocalDateTime.now());
        this.save(user);
    }


}
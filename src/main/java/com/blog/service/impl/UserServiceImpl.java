package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.model.dto.user.LoginDTO;
import com.blog.model.dto.user.RegisterDTO;
import com.blog.model.dto.user.UserDTO;
import com.blog.enums.UserEnum;
import com.blog.exception.APIException;
import com.blog.mapper.UserMapper;
import com.blog.pojo.User;
import com.blog.service.UserService;
import com.blog.utils.Constant;
import com.blog.utils.JwtUtils;
import com.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDTO login(LoginDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        queryWrapper.eq(User::getPassword, MD5Utils.md5(password));

        User user = this.getOne(queryWrapper);

        if (user == null) {
            throw new APIException(Constant.LOGIN_ERROR);
        }

        user.setLastLoginTime(LocalDateTime.now());
        this.updateById(user);

        Map<String, Object> claims = new HashMap<>(16);
        claims.put("userId", user.getId());
        String token = jwtUtils.createToken(claims, 720);
        user.setPassword(null);

        UserDTO userDTO = new UserDTO();
        userDTO.setToken(token);
        userDTO.setUser(user);
        return userDTO;
    }

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
        user.setPassword(MD5Utils.md5(password));
        user.setNickname(UUID.randomUUID().toString().replaceAll("-", ""));
        user.setIsDisable(UserEnum.NORMAL.getCode());
        user.setLastLoginTime(LocalDateTime.now());
        this.save(user);
    }


}
package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.user.LoginDTO;
import com.blog.model.dto.user.RegisterDTO;
import com.blog.model.dto.user.UserDTO;
import com.blog.pojo.User;

public interface UserService extends IService<User> {
    UserDTO login(LoginDTO dto);

    void register(RegisterDTO dto);
}
package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.user.RegisterDTO;
import com.blog.pojo.User;

public interface UserService extends IService<User> {
    void register(RegisterDTO dto);
}
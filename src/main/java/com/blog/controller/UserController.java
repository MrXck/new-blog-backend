package com.blog.controller;

import com.blog.model.dto.user.LoginDTO;
import com.blog.model.dto.user.RegisterDTO;
import com.blog.model.dto.user.UserDTO;
import com.blog.service.UserService;
import com.blog.utils.NoAuthorization;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "用户相关")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @NoAuthorization
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public UserDTO login(@RequestBody @Valid LoginDTO dto) {
        return userService.login(dto);
    }

    @NoAuthorization
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterDTO dto) {
        userService.register(dto);
    }

}
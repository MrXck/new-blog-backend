package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.user.RegisterDTO;
import com.blog.model.dto.user.UpdateDTO;
import com.blog.model.dto.user.UserDTO;
import com.blog.service.UserService;
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

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterDTO dto) {
        userService.register(dto);
    }

    @ApiOperation("分页获取标签")
    @PostMapping("/page")
    public UserDTO page(@RequestBody @Valid PageDTO dto) {
        return userService.getByPage(dto);
    }

    @ApiOperation("根据id修改用户禁用状态")
    @GetMapping("/disable/{id}/{isDisable}")
    public void updateFeatured(@PathVariable("id") Long id, @PathVariable("isDisable") Integer isDisable) {
        userService.disable(id, isDisable);
    }

    @ApiOperation("根据id修改用户信息")
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateDTO dto) {
        userService.edit(dto);
    }

    @ApiOperation("分页获取当前在线用户")
    @PostMapping("/online/page")
    public UserDTO onlinePage(@RequestBody @Valid PageDTO dto) {
        return userService.onlinePage(dto);
    }

    @ApiOperation("根据id下线用户")
    @DeleteMapping("/offline/{id}")
    public void offline(@PathVariable("id") Long id) {
        userService.offline(id);
    }

}
package com.blog.controller;

import com.blog.model.dto.userRole.UserRoleDTO;
import com.blog.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "UserRole相关")
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation("根据UserId获取Role")
    @GetMapping("/getByUserId/{userId}")
    public UserRoleDTO getByUserId(@PathVariable("userId") Long userId) {
        return userRoleService.getByUserId(userId);
    }

}
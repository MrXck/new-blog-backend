package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.userRole.AddDTO;
import com.blog.model.dto.userRole.UpdateDTO;
import com.blog.model.dto.userRole.UserRoleDTO;
import com.blog.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "UserRole相关")
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation("分页获取UserRole")
    @PostMapping("/page")
    public UserRoleDTO page(@RequestBody @Valid PageDTO dto) {
        return userRoleService.getByPage(dto);
    }

    @ApiOperation("添加UserRole")
    @PostMapping("/add")
    public void add(@RequestBody @Valid AddDTO dto) {
        userRoleService.add(dto);
    }

    @ApiOperation("删除UserRole")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userRoleService.deleteById(id);
    }

    @ApiOperation("修改UserRole")
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateDTO dto) {
        userRoleService.edit(dto);
    }

    @ApiOperation("获取所有UserRole")
    @GetMapping("/")
    public UserRoleDTO list() {
        return userRoleService.all();
    }

    @ApiOperation("根据id获取UserRole")
    @GetMapping("/{id}")
    public UserRoleDTO getById(@PathVariable("id") Long id) {
        return userRoleService.findById(id);
    }

}
package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.roleMenu.AddDTO;
import com.blog.model.dto.roleMenu.RoleMenuDTO;
import com.blog.model.dto.roleMenu.UpdateDTO;
import com.blog.service.RoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "RoleMenu相关")
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {

    @Autowired
    private RoleMenuService roleMenuService;

    @ApiOperation("分页获取RoleMenu")
    @PostMapping("/page")
    public RoleMenuDTO page(@RequestBody @Valid PageDTO dto) {
        return roleMenuService.getByPage(dto);
    }

    @ApiOperation("添加RoleMenu")
    @PostMapping("/add")
    public void add(@RequestBody @Valid AddDTO dto) {
        roleMenuService.add(dto);
    }

    @ApiOperation("删除RoleMenu")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        roleMenuService.deleteById(id);
    }

    @ApiOperation("修改RoleMenu")
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateDTO dto) {
        roleMenuService.edit(dto);
    }

    @ApiOperation("获取所有RoleMenu")
    @GetMapping("/")
    public RoleMenuDTO list() {
        return roleMenuService.all();
    }

    @ApiOperation("根据id获取RoleMenu")
    @GetMapping("/{id}")
    public RoleMenuDTO getById(@PathVariable("id") Long id) {
        return roleMenuService.findById(id);
    }

}
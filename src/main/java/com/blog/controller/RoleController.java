package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.role.AddDTO;
import com.blog.model.dto.role.RoleDTO;
import com.blog.model.dto.role.UpdateDTO;
import com.blog.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Role相关")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("分页获取Role")
    @PostMapping("/page")
    public RoleDTO page(@RequestBody @Valid PageDTO dto) {
        return roleService.getByPage(dto);
    }

    @ApiOperation("获取所有Role")
    @GetMapping("/all")
    public RoleDTO all() {
        return roleService.all();
    }

    @ApiOperation("添加Role")
    @PostMapping("/insert")
    public void insert(@RequestBody @Valid AddDTO dto) {
        roleService.add(dto);
    }

    @ApiOperation("删除Role")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        roleService.deleteById(id);
    }

    @ApiOperation("修改Role")
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateDTO dto) {
        roleService.edit(dto);
    }

    @ApiOperation("获取所有Role")
    @GetMapping("/")
    public RoleDTO list() {
        return roleService.all();
    }

    @ApiOperation("根据id获取Role")
    @GetMapping("/{id}")
    public RoleDTO getById(@PathVariable("id") Long id) {
        return roleService.findById(id);
    }

    @ApiOperation("修改角色是否禁用")
    @GetMapping("/disable/{id}/{isDisable}")
    public void updateDisable(@PathVariable("id") Long id, @PathVariable("isDisable") Integer isDisable) {
        roleService.disable(id, isDisable);
    }

}
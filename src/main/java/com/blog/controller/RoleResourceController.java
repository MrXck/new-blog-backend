package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.roleResource.AddDTO;
import com.blog.model.dto.roleResource.RoleResourceDTO;
import com.blog.model.dto.roleResource.UpdateDTO;
import com.blog.service.RoleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "RoleResource相关")
@RestController
@RequestMapping("/roleResource")
public class RoleResourceController {

    @Autowired
    private RoleResourceService roleResourceService;

    @ApiOperation("分页获取RoleResource")
    @PostMapping("/page")
    public RoleResourceDTO page(@RequestBody @Valid PageDTO dto) {
        return roleResourceService.getByPage(dto);
    }

    @ApiOperation("添加RoleResource")
    @PostMapping("/add")
    public void add(@RequestBody @Valid AddDTO dto) {
        roleResourceService.add(dto);
    }

    @ApiOperation("删除RoleResource")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        roleResourceService.deleteById(id);
    }

    @ApiOperation("修改RoleResource")
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateDTO dto) {
        roleResourceService.edit(dto);
    }

    @ApiOperation("获取所有RoleResource")
    @GetMapping("/")
    public RoleResourceDTO list() {
        return roleResourceService.all();
    }

    @ApiOperation("根据id获取RoleResource")
    @GetMapping("/{id}")
    public RoleResourceDTO getById(@PathVariable("id") Long id) {
        return roleResourceService.findById(id);
    }

}
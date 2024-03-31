package com.blog.controller;

import com.blog.model.dto.roleResource.RoleResourceDTO;
import com.blog.service.RoleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "RoleResource相关")
@RestController
@RequestMapping("/roleResource")
public class RoleResourceController {

    @Autowired
    private RoleResourceService roleResourceService;

    @ApiOperation("获取所有RoleResource")
    @GetMapping("/")
    public RoleResourceDTO list() {
        return roleResourceService.all();
    }

    @ApiOperation("根据角色id获取RoleResource")
    @GetMapping("/{roleId}")
    public RoleResourceDTO getByRoleIdId(@PathVariable("roleId") Long roleId) {
        return roleResourceService.findByRoleId(roleId);
    }

}
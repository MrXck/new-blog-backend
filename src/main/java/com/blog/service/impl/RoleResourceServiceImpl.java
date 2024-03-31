package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.RoleResourceMapper;
import com.blog.model.dto.roleResource.RoleResourceDTO;
import com.blog.pojo.RoleResource;
import com.blog.service.RoleResourceService;
import org.springframework.stereotype.Service;

@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

    @Override
    public RoleResourceDTO all() {
        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();
        roleResourceDTO.setRoleResources(this.list());
        return roleResourceDTO;
    }

    @Override
    public RoleResourceDTO findByRoleId(Long roleId) {
        LambdaQueryWrapper<RoleResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleResource::getRoleId, roleId);
        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();
        roleResourceDTO.setRoleResources(this.list(queryWrapper));
        return roleResourceDTO;
    }

}
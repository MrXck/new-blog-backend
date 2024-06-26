package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.RoleResourceMapper;
import com.blog.model.dto.roleResource.RoleResourceDTO;
import com.blog.pojo.RoleResource;
import com.blog.secutiry.FilterInvocationSecurityMetadataSourceImpl;
import com.blog.service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

    @Autowired
    private FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource;

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

    @Override
    public RoleResourceDTO getResourcesByRoleId(Long roleId) {
        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();
        LambdaQueryWrapper<RoleResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleResource::getRoleId, roleId);
        queryWrapper.select(RoleResource.class, i -> "resource_id".equals(i.getColumn()));
        roleResourceDTO.setRoleResources(this.list(queryWrapper));
        return roleResourceDTO;
    }

    @Override
    public void saveResourceByRoleId(Long roleId, List<Long> resourceIds) {
        List<RoleResource> roleResources = new ArrayList<>();
        for (Long resourceId : resourceIds) {
            RoleResource roleResource = new RoleResource();
            roleResource.setResourceId(resourceId);
            roleResource.setRoleId(roleId);
            roleResources.add(roleResource);
        }
        this.saveBatch(roleResources);
    }

    @Override
    public void updateResourceByRoleId(Long roleId, List<Long> resourceIds) {
        deleteResourceByRoleId(roleId);

        List<RoleResource> roleResources = new ArrayList<>();
        for (Long resourceId : resourceIds) {
            RoleResource roleResource = new RoleResource();
            roleResource.setResourceId(resourceId);
            roleResource.setRoleId(roleId);
            roleResources.add(roleResource);
        }
        this.saveBatch(roleResources);
        filterInvocationSecurityMetadataSource.loadResourceRoleList();
    }

    @Override
    public void deleteResourceByRoleId(Long roleId) {
        LambdaQueryWrapper<RoleResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleResource::getRoleId, roleId);
        this.remove(queryWrapper);
    }

}
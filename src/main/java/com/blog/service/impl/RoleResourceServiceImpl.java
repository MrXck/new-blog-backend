package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.RoleResourceMapper;
import com.blog.model.dto.roleResource.RoleResourceDTO;
import com.blog.pojo.RoleResource;
import com.blog.service.RoleResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public RoleResourceDTO getResourcesByRoleId(Long roleId) {
        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();
        roleResourceDTO.setResources(baseMapper.getResourcesByRoleId(roleId));
        return roleResourceDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public void updateResourceByRoleId(Long roleId, List<Long> resourceIds) {
        LambdaQueryWrapper<RoleResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleResource::getRoleId, roleId);
        this.remove(queryWrapper);

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
    @Transactional(rollbackFor = Exception.class)
    public void deleteResourceByRoleId(Long roleId) {
        LambdaQueryWrapper<RoleResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleResource::getRoleId, roleId);
        this.remove(queryWrapper);
    }

}
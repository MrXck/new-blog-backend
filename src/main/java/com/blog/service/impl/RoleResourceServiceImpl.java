package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.RoleResourceMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.roleResource.AddDTO;
import com.blog.model.dto.roleResource.RoleResourceDTO;
import com.blog.model.dto.roleResource.UpdateDTO;
import com.blog.pojo.RoleResource;
import com.blog.service.RoleResourceService;
import com.blog.utils.UserThreadLocal;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

    @Override
    public RoleResourceDTO getByPage(PageDTO dto) {
        Page<RoleResource> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<RoleResource> queryWrapper = new LambdaQueryWrapper<>();
        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();
        roleResourceDTO.setPage(this.page(page, queryWrapper));
        return roleResourceDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AddDTO dto) {
        RoleResource roleResource = new RoleResource();
        BeanUtils.copyProperties(dto, roleResource);
        this.save(roleResource);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(UpdateDTO dto) {
        RoleResource roleResource = new RoleResource();
        BeanUtils.copyProperties(dto, roleResource);
        this.updateById(roleResource);
    }

    @Override
    public RoleResourceDTO findById(Long id) {
        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();
        roleResourceDTO.setRoleResource(this.getById(id));
        return roleResourceDTO;
    }

    @Override
    public RoleResourceDTO all() {
        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();
        roleResourceDTO.setRoleResources(this.list());
        return roleResourceDTO;
    }

}
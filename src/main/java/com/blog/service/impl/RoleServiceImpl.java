package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.enums.role.RoleEnum;
import com.blog.mapper.RoleMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.role.AddDTO;
import com.blog.model.dto.role.RoleDTO;
import com.blog.model.dto.role.UpdateDTO;
import com.blog.pojo.Role;
import com.blog.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public RoleDTO getByPage(PageDTO dto) {
        Page<Role> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        String keyword = dto.getKeyword();
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Role::getName, keyword);
        queryWrapper.orderByDesc(Role::getUpdateTime);
        queryWrapper.orderByDesc(Role::getCreateTime);
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setPage(this.page(page, queryWrapper));
        return roleDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AddDTO dto) {
        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        role.setIsDisable(RoleEnum.NORMAL.getCode());
        this.save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(UpdateDTO dto) {
        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        this.updateById(role);
    }

    @Override
    public RoleDTO findById(Long id) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRole(this.getById(id));
        return roleDTO;
    }

    @Override
    public RoleDTO all() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoles(this.list());
        return roleDTO;
    }

}
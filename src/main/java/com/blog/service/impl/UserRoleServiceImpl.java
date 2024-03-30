package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.UserRoleMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.userRole.AddDTO;
import com.blog.model.dto.userRole.UpdateDTO;
import com.blog.model.dto.userRole.UserRoleDTO;
import com.blog.pojo.UserRole;
import com.blog.service.UserRoleService;
import com.blog.utils.UserThreadLocal;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public UserRoleDTO getByPage(PageDTO dto) {
        Page<UserRole> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setPage(this.page(page, queryWrapper));
        return userRoleDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AddDTO dto) {
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(dto, userRole);
        userRole.setUserId(UserThreadLocal.get());
        this.save(userRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(UpdateDTO dto) {
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(dto, userRole);
        this.updateById(userRole);
    }

    @Override
    public UserRoleDTO findById(Long id) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setUserRole(this.getById(id));
        return userRoleDTO;
    }

    @Override
    public UserRoleDTO all() {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setUserRoles(this.list());
        return userRoleDTO;
    }

}
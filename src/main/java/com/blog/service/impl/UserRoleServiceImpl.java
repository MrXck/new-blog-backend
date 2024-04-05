package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.UserRoleMapper;
import com.blog.model.dto.userRole.UserRoleDTO;
import com.blog.pojo.UserRole;
import com.blog.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public void deleteByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, userId);
        this.remove(queryWrapper);
    }

    @Override
    public void updateByUserId(Long userId, List<Long> roleIds) {
        deleteByUserId(userId);

        List<UserRole> userRoles = new ArrayList<>();

        for (Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
        }
        this.saveBatch(userRoles);
    }

    @Override
    public UserRoleDTO getByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, userId);
        queryWrapper.select(UserRole.class, i -> "role_id".equals(i.getColumn()));
        List<UserRole> list = this.list(queryWrapper);
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setUserRoles(list);
        return userRoleDTO;
    }

}
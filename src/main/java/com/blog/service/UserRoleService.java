package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.userRole.UserRoleDTO;
import com.blog.pojo.UserRole;

import java.util.List;

public interface UserRoleService extends IService<UserRole> {

    void deleteByUserId(Long userId);

    void updateByUserId(Long userId, List<Long> roleIds);

    UserRoleDTO getByUserId(Long userId);
}
package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.roleResource.RoleResourceDTO;
import com.blog.pojo.RoleResource;

public interface RoleResourceService extends IService<RoleResource> {

    RoleResourceDTO all();

    RoleResourceDTO findByRoleId(Long roleId);
}
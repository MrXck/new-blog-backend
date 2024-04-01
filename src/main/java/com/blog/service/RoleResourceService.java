package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.roleResource.RoleResourceDTO;
import com.blog.pojo.RoleResource;

import java.util.List;

public interface RoleResourceService extends IService<RoleResource> {

    RoleResourceDTO all();

    RoleResourceDTO findByRoleId(Long roleId);

    RoleResourceDTO getResourcesByRoleId(Long roleId);

    void saveResourceByRoleId(Long roleId, List<Long> resourceIds);

    void updateResourceByRoleId(Long roleId, List<Long> resourceIds);
}
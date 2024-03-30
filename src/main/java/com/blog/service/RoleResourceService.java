package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.roleResource.AddDTO;
import com.blog.model.dto.roleResource.RoleResourceDTO;
import com.blog.model.dto.roleResource.UpdateDTO;
import com.blog.pojo.RoleResource;

public interface RoleResourceService extends IService<RoleResource> {

    RoleResourceDTO getByPage(PageDTO dto);

    void add(AddDTO dto);

    void deleteById(Long id);

    void edit(UpdateDTO dto);

    RoleResourceDTO findById(Long id);

    RoleResourceDTO all();

}
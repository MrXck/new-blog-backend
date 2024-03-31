package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.role.AddDTO;
import com.blog.model.dto.role.RoleDTO;
import com.blog.model.dto.role.UpdateDTO;
import com.blog.pojo.Role;

public interface RoleService extends IService<Role> {

    RoleDTO getByPage(PageDTO dto);

    void add(AddDTO dto);

    void deleteById(Long id);

    void edit(UpdateDTO dto);

    RoleDTO findById(Long id);

    RoleDTO all();

    void disable(Long id, Integer isDisable);
}
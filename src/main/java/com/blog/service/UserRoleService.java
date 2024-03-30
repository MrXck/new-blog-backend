package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.userRole.AddDTO;
import com.blog.model.dto.userRole.UpdateDTO;
import com.blog.model.dto.userRole.UserRoleDTO;
import com.blog.pojo.UserRole;

public interface UserRoleService extends IService<UserRole> {

    UserRoleDTO getByPage(PageDTO dto);

    void add(AddDTO dto);

    void deleteById(Long id);

    void edit(UpdateDTO dto);

    UserRoleDTO findById(Long id);

    UserRoleDTO all();

}
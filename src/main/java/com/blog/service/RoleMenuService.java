package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.roleMenu.AddDTO;
import com.blog.model.dto.roleMenu.RoleMenuDTO;
import com.blog.model.dto.roleMenu.UpdateDTO;
import com.blog.pojo.RoleMenu;

public interface RoleMenuService extends IService<RoleMenu> {

    RoleMenuDTO getByPage(PageDTO dto);

    void add(AddDTO dto);

    void deleteById(Long id);

    void edit(UpdateDTO dto);

    RoleMenuDTO findById(Long id);

    RoleMenuDTO all();

}
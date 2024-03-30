package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.menu.AddDTO;
import com.blog.model.dto.menu.MenuDTO;
import com.blog.model.dto.menu.UpdateDTO;
import com.blog.pojo.Menu;

public interface MenuService extends IService<Menu> {

    MenuDTO getByPage(PageDTO dto);

    void add(AddDTO dto);

    void deleteById(Long id);

    void edit(UpdateDTO dto);

    MenuDTO findById(Long id);

    MenuDTO all();

}
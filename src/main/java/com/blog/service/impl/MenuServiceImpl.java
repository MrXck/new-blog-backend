package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.MenuMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.menu.AddDTO;
import com.blog.model.dto.menu.MenuDTO;
import com.blog.model.dto.menu.UpdateDTO;
import com.blog.pojo.Menu;
import com.blog.service.MenuService;
import com.blog.utils.UserThreadLocal;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public MenuDTO getByPage(PageDTO dto) {
        Page<Menu> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        String keyword = dto.getKeyword();
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Menu::getName, keyword);
        queryWrapper.orderByDesc(Menu::getUpdateTime);
        queryWrapper.orderByDesc(Menu::getCreateTime);
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setPage(this.page(page, queryWrapper));
        return menuDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AddDTO dto) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(dto, menu);
        this.save(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(UpdateDTO dto) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(dto, menu);
        this.updateById(menu);
    }

    @Override
    public MenuDTO findById(Long id) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setMenu(this.getById(id));
        return menuDTO;
    }

    @Override
    public MenuDTO all() {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setMenus(this.list());
        return menuDTO;
    }

}
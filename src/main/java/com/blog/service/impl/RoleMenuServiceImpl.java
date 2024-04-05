package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.RoleMenuMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.roleMenu.AddDTO;
import com.blog.model.dto.roleMenu.RoleMenuDTO;
import com.blog.model.dto.roleMenu.UpdateDTO;
import com.blog.pojo.RoleMenu;
import com.blog.service.RoleMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public RoleMenuDTO getByPage(PageDTO dto) {
        Page<RoleMenu> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(RoleMenu::getUpdateTime);
        queryWrapper.orderByDesc(RoleMenu::getCreateTime);
        RoleMenuDTO roleMenuDTO = new RoleMenuDTO();
        roleMenuDTO.setPage(this.page(page, queryWrapper));
        return roleMenuDTO;
    }

    @Override
    public void add(AddDTO dto) {
        RoleMenu roleMenu = new RoleMenu();
        BeanUtils.copyProperties(dto, roleMenu);
        this.save(roleMenu);
    }

    @Override
    public void deleteById(Long id) {
        this.removeById(id);
    }

    @Override
    public void edit(UpdateDTO dto) {
        RoleMenu roleMenu = new RoleMenu();
        BeanUtils.copyProperties(dto, roleMenu);
        this.updateById(roleMenu);
    }

    @Override
    public RoleMenuDTO findById(Long id) {
        RoleMenuDTO roleMenuDTO = new RoleMenuDTO();
        roleMenuDTO.setRoleMenu(this.getById(id));
        return roleMenuDTO;
    }

    @Override
    public RoleMenuDTO all() {
        RoleMenuDTO roleMenuDTO = new RoleMenuDTO();
        roleMenuDTO.setRoleMenus(this.list());
        return roleMenuDTO;
    }

}
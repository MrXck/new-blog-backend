package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.enums.role.RoleEnum;
import com.blog.enums.role.RoleErrorEnum;
import com.blog.enums.userRole.UserRoleEnum;
import com.blog.exception.APIException;
import com.blog.mapper.RoleMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.role.AddDTO;
import com.blog.model.dto.role.RoleDTO;
import com.blog.model.dto.role.UpdateDTO;
import com.blog.pojo.Role;
import com.blog.pojo.UserRole;
import com.blog.service.RoleResourceService;
import com.blog.service.RoleService;
import com.blog.service.UserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleResourceService roleResourceService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public RoleDTO getByPage(PageDTO dto) {
        Page<Role> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        String keyword = dto.getKeyword();
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Role::getName, keyword);
        queryWrapper.orderByDesc(Role::getUpdateTime);
        queryWrapper.orderByDesc(Role::getCreateTime);
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setPage(this.page(page, queryWrapper));
        return roleDTO;
    }

    @Override
    public void add(AddDTO dto) {
        String name = dto.getName();

        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getName, name);
        if (this.count(queryWrapper) > 0) {
            throw new APIException(RoleErrorEnum.CREATE_ROLE_ERROR.getValue());
        }

        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        role.setIsDisable(RoleEnum.NORMAL.getCode());
        this.save(role);

        roleResourceService.saveResourceByRoleId(role.getId(), dto.getResourceIds());
    }

    @Override
    public void deleteById(Long id) {
        LambdaQueryWrapper<UserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userRoleLambdaQueryWrapper.eq(UserRole::getRoleId, id);
        if (userRoleService.count(userRoleLambdaQueryWrapper) != 0) {
            throw new APIException(UserRoleEnum.DELETE_ROLE_ERROR.getValue());
        }

        this.removeById(id);
        roleResourceService.deleteResourceByRoleId(id);
    }

    @Override
    public void edit(UpdateDTO dto) {
        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        this.updateById(role);
        roleResourceService.updateResourceByRoleId(role.getId(), dto.getResourceIds());
    }

    @Override
    public RoleDTO findById(Long id) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRole(this.getById(id));
        return roleDTO;
    }

    @Override
    public RoleDTO all() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoles(this.list());
        return roleDTO;
    }

    @Override
    public void disable(Long id, Integer isDisable) {
        if (!RoleEnum.DISABLE.getCode().equals(isDisable) && !RoleEnum.NORMAL.getCode().equals(isDisable)) {
            throw new APIException(RoleErrorEnum.IS_DISABLE_ERROR.getValue());
        }
        LambdaUpdateWrapper<Role> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Role::getId, id);
        updateWrapper.set(Role::getIsDisable, isDisable);
        this.update(updateWrapper);
    }

}
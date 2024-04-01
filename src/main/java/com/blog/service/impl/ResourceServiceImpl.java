package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.enums.resource.ResourceEnum;
import com.blog.enums.resource.ResourceErrorEnum;
import com.blog.exception.APIException;
import com.blog.mapper.ResourceMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.resource.*;
import com.blog.pojo.Resource;
import com.blog.secutiry.FilterInvocationSecurityMetadataSourceImpl;
import com.blog.service.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Autowired
    private FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource;

    @Override
    public ResourceDTO getByPage(PageDTO dto) {
        Page<Resource> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        String keyword = dto.getKeyword();
        LambdaQueryWrapper<Resource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Resource::getName, keyword);
        queryWrapper.orderByDesc(Resource::getUpdateTime);
        queryWrapper.orderByDesc(Resource::getCreateTime);
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setPage(this.page(page, queryWrapper));
        return resourceDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AddResourceDTO dto) {
        Integer isAnonymous = dto.getIsAnonymous();
        if (!ResourceEnum.IS_ANONYMOUS.getCode().equals(isAnonymous) && !ResourceEnum.IS_NOT_ANONYMOUS.getCode().equals(isAnonymous)) {
            throw new APIException(ResourceErrorEnum.ANONYMOUS_ERROR.getValue());
        }

        if (this.getById(dto.getParentId()) == null) {
            throw new APIException(ResourceErrorEnum.PARENT_RESOURCE_ERROR.getValue());
        }

        LambdaQueryWrapper<Resource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resource::getName, dto.getName());
        queryWrapper.eq(Resource::getParentId, dto.getParentId());
        if (this.count(queryWrapper) > 0) {
            throw new APIException(ResourceErrorEnum.CREATE_RESOURCE_ERROR.getValue());
        }

        Resource resource = new Resource();
        BeanUtils.copyProperties(dto, resource);
        this.save(resource);
        filterInvocationSecurityMetadataSource.loadResourceRoleList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        this.removeById(id);
        filterInvocationSecurityMetadataSource.loadResourceRoleList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(UpdateDTO dto) {
        Resource resource = new Resource();
        BeanUtils.copyProperties(dto, resource);
        this.updateById(resource);
        filterInvocationSecurityMetadataSource.loadResourceRoleList();
    }

    @Override
    public ResourceDTO findById(Long id) {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setResource(this.getById(id));
        return resourceDTO;
    }

    @Override
    public ResourceDTO all() {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setResources(this.list());
        return resourceDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addResourceParent(AddResourceParentDTO dto) {
        LambdaQueryWrapper<Resource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resource::getName, dto.getName());
        queryWrapper.isNull(Resource::getParentId);
        if (this.count(queryWrapper) > 0) {
            throw new APIException(ResourceErrorEnum.CREATE_RESOURCE_ERROR.getValue());
        }

        Resource resource = new Resource();
        BeanUtils.copyProperties(dto, resource);
        resource.setIsAnonymous(ResourceEnum.IS_ANONYMOUS.getCode());
        this.save(resource);
        filterInvocationSecurityMetadataSource.loadResourceRoleList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editParent(UpdateParentDTO dto) {
        Long id = dto.getId();
        Resource resource = this.getById(id);

        if (resource.getParentId() != null) {
            throw new APIException(ResourceErrorEnum.NOT_FOUND_PARENT_ERROR.getValue());
        }

        resource.setName(dto.getName());
        this.updateById(resource);
        filterInvocationSecurityMetadataSource.loadResourceRoleList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteParentById(Long id) {
        this.removeById(id);

        LambdaQueryWrapper<Resource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resource::getParentId, id);
        this.remove(queryWrapper);
        filterInvocationSecurityMetadataSource.loadResourceRoleList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAnonymous(Long id, Integer anonymous) {
        if (!ResourceEnum.IS_ANONYMOUS.getCode().equals(anonymous) && !ResourceEnum.IS_NOT_ANONYMOUS.getCode().equals(anonymous)) {
            throw new APIException(ResourceErrorEnum.ANONYMOUS_ERROR.getValue());
        }

        LambdaUpdateWrapper<Resource> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Resource::getId, id);
        updateWrapper.set(Resource::getIsAnonymous, anonymous);
        this.update(updateWrapper);
        filterInvocationSecurityMetadataSource.loadResourceRoleList();
    }

}
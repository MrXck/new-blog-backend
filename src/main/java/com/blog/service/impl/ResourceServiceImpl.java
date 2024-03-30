package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.ResourceMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.resource.AddDTO;
import com.blog.model.dto.resource.ResourceDTO;
import com.blog.model.dto.resource.UpdateDTO;
import com.blog.pojo.Resource;
import com.blog.service.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Override
    public ResourceDTO getByPage(PageDTO dto) {
        Page<Resource> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        String keyword = dto.getKeyword();
        LambdaQueryWrapper<Resource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Resource::getUpdateTime);
        queryWrapper.orderByDesc(Resource::getCreateTime);
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setPage(this.page(page, queryWrapper));
        return resourceDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AddDTO dto) {
        Resource resource = new Resource();
        BeanUtils.copyProperties(dto, resource);
        this.save(resource);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(UpdateDTO dto) {
        Resource resource = new Resource();
        BeanUtils.copyProperties(dto, resource);
        this.updateById(resource);
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

}
package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.category.AddDTO;
import com.blog.model.dto.category.CategoryDTO;
import com.blog.model.dto.category.UpdateDTO;
import com.blog.enums.category.CategoryEnum;
import com.blog.exception.APIException;
import com.blog.mapper.CategoryMapper;
import com.blog.pojo.Category;
import com.blog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public CategoryDTO getByPage(PageDTO dto) {
        Page<Category> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        String keyword = dto.getKeyword();

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Category::getName, keyword);
        queryWrapper.orderByDesc(Category::getUpdateTime);
        queryWrapper.orderByDesc(Category::getCreateTime);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setPage(this.page(page, queryWrapper));
        return categoryDTO;
    }

    @Override
    public void add(AddDTO dto) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getName, dto.getName());
        int count = this.count(queryWrapper);
        if (count != 0) {
            throw new APIException(CategoryEnum.ALREADY_EXIST_ERROR.getValue());
        }
        Category category = new Category();
        category.setName(dto.getName());
        this.save(category);
    }

    @Override
    public void delete(Long id) {
        this.removeById(id);
    }

    @Override
    public void edit(UpdateDTO dto) {
        Category category = new Category();
        BeanUtils.copyProperties(dto, category);
        this.updateById(category);
    }

    @Override
    public CategoryDTO all() {
        List<Category> list = this.list();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setList(list);
        return categoryDTO;
    }

    @Override
    public CategoryDTO allCount() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCount(this.count());
        return categoryDTO;
    }
}
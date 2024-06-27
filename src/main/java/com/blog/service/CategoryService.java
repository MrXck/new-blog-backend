package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.category.AddDTO;
import com.blog.model.dto.category.CategoryDTO;
import com.blog.model.dto.category.UpdateDTO;
import com.blog.pojo.Category;

public interface CategoryService extends IService<Category> {
    CategoryDTO getByPage(PageDTO dto);

    void add(AddDTO dto);

    void delete(Long id);

    void edit(UpdateDTO dto);

    CategoryDTO all();

    CategoryDTO allCount();
}
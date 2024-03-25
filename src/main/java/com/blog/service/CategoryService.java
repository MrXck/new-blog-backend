package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.dto.PageDTO;
import com.blog.dto.category.AddDTO;
import com.blog.dto.category.CategoryDTO;
import com.blog.dto.category.UpdateDTO;
import com.blog.pojo.Category;

public interface CategoryService extends IService<Category> {
    CategoryDTO getByPage(PageDTO dto);

    void add(AddDTO dto);

    void delete(Long id);

    void edit(UpdateDTO dto);
}
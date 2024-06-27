package com.blog.model.dto.category;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {

    private Page<Category> page;

    private List<Category> list;

    private Integer count;
}

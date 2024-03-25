package com.blog.dto.category;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.Category;
import lombok.Data;

@Data
public class CategoryDTO {

    private Page<Category> page;
}

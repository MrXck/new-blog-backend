package com.blog.model.dto.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.Article;
import lombok.Data;

import java.util.List;

@Data
public class ArticleDTO {

    private Page<Article> page;

    private Article article;

    private List<Article> articles;

    private Long id;
}

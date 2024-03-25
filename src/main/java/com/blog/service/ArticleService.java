package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.dto.PageDTO;
import com.blog.dto.article.AddDTO;
import com.blog.dto.article.ArticleDTO;
import com.blog.dto.article.UpdateDTO;
import com.blog.pojo.Article;

public interface ArticleService extends IService<Article> {
    ArticleDTO getByPage(PageDTO dto);

    ArticleDTO add(AddDTO dto);

    void deleteById(Long id);

    ArticleDTO getByPageAdmin(PageDTO dto);

    void edit(UpdateDTO dto);

    ArticleDTO findById(Long id);

    Article checkAndGetArticle(Long id);
}
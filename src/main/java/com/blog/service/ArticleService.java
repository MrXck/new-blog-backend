package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.article.AddDTO;
import com.blog.model.dto.article.ArticleDTO;
import com.blog.model.dto.article.UpdateDTO;
import com.blog.pojo.Article;

public interface ArticleService extends IService<Article> {
    ArticleDTO getByPage(PageDTO dto);

    ArticleDTO add(AddDTO dto);

    void deleteById(Long id);

    ArticleDTO getByPageAdmin(PageDTO dto);

    void edit(UpdateDTO dto);

    ArticleDTO findById(Long id);

    Article checkAndGetArticle(Long id);

    void top(Long id, Integer isTop);

    void featured(Long id, Integer isFeatured);

    ArticleDTO getNew();

    ArticleDTO getNewFeatured();

    ArticleDTO allCount();

    ArticleDTO archive(PageDTO dto);
}
package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.dto.articleTag.ArticleTagDTO;
import com.blog.dto.articleTag.SaveDTO;
import com.blog.pojo.ArticleTag;

public interface ArticleTagService extends IService<ArticleTag> {

    ArticleTagDTO getByArticleId(Long articleId);

    void saveByArticleId(SaveDTO dto);

    void deleteByArticleId(Long articleId);

    void deleteByTagId(Long tagId);
}
package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.articleTag.ArticleTagDTO;
import com.blog.model.dto.articleTag.SaveDTO;
import com.blog.model.vo.ArticleTagVO;
import com.blog.pojo.ArticleTag;

import java.util.Collection;
import java.util.List;

public interface ArticleTagService extends IService<ArticleTag> {

    ArticleTagDTO getByArticleId(Long articleId);

    void saveByArticleId(SaveDTO dto);

    void deleteByArticleId(Long articleId);

    void deleteByTagId(Long tagId);

    List<ArticleTagVO> getTagListByArticleIds(Collection<Long> articleIds);

    ArticleTagVO getArticleByTagId(Long tagId, PageDTO pageDTO);
}
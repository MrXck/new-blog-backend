package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.dto.PageDTO;
import com.blog.dto.article.AddDTO;
import com.blog.dto.article.ArticleDTO;
import com.blog.dto.article.UpdateDTO;
import com.blog.enums.ArticleEnum;
import com.blog.exception.APIException;
import com.blog.pojo.Article;
import com.blog.mapper.ArticleMapper;
import com.blog.service.ArticleService;
import com.blog.utils.UserThreadLocal;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public ArticleDTO getByPage(PageDTO dto) {
        Page<Article> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        String keyword = dto.getKeyword();

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Article::getTitle, keyword);
        queryWrapper.eq(Article::getIsDelete, ArticleEnum.NOT_DELETE.getCode());
        queryWrapper.select(Article.class, i -> !"content".equals(i.getColumn()));
        queryWrapper.orderByDesc(Article::getUpdateTime);
        queryWrapper.orderByDesc(Article::getCreateTime);
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setPage(this.page(page, queryWrapper));
        return articleDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleDTO add(AddDTO dto) {
        String desc = dto.getDigest();
        Article article = new Article();

        BeanUtils.copyProperties(dto, article);

        article.setUserId(UserThreadLocal.get());
        if (desc == null || desc.isEmpty()) {
            int length = dto.getContent().length();
            // 设置默认描述
            article.setDigest(dto.getContent().substring(0, Math.min(length, 500)));
        }
        article.setIsDelete(ArticleEnum.NOT_DELETE.getCode());
        this.save(article);
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        return articleDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getUserId, UserThreadLocal.get());
        updateWrapper.set(Article::getIsDelete, ArticleEnum.DELETE.getCode());
        this.update(updateWrapper);
    }

    @Override
    public ArticleDTO getByPageAdmin(PageDTO dto) {
        Page<Article> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        String keyword = dto.getKeyword();
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Article::getTitle, keyword);
        queryWrapper.select(Article.class, i -> !"content".equals(i.getColumn()));
        queryWrapper.orderByDesc(Article::getUpdateTime);
        queryWrapper.orderByDesc(Article::getCreateTime);
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setPage(this.page(page, queryWrapper));
        return articleDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(UpdateDTO dto) {
        String desc = dto.getDigest();
        Article article = new Article();
        BeanUtils.copyProperties(dto, article);
        if (desc == null || desc.isEmpty()) {
            int length = dto.getContent().length();
            // 设置默认描述
            article.setDigest(dto.getContent().substring(0, Math.min(500, length)));
        }
        this.updateById(article);
    }

    @Override
    public ArticleDTO findById(Long id) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setArticle(this.getById(id));
        return articleDTO;
    }

    @Override
    public Article checkAndGetArticle(Long id) {
        Article article = this.getById(id);
        if (article == null) {
            throw new APIException("该文章不存在");
        }
        return article;
    }
}
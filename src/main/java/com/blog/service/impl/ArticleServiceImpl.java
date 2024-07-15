package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.enums.article.ArticleEnum;
import com.blog.enums.article.ArticleErrorEnum;
import com.blog.exception.APIException;
import com.blog.mapper.ArticleMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.article.AddDTO;
import com.blog.model.dto.article.ArticleDTO;
import com.blog.model.dto.article.UpdateDTO;
import com.blog.model.vo.ArticleVO;
import com.blog.pojo.Article;
import com.blog.service.ArticleService;
import com.blog.service.ArticleTagService;
import com.blog.utils.UserThreadLocal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    @Lazy
    private ArticleTagService articleTagService;

    @Override
    public ArticleDTO getByPage(PageDTO dto) {
        String keyword = dto.getKeyword();
        ArticleDTO articleDTO = new ArticleDTO();

        List<ArticleVO> articleVOS = this.baseMapper.getByPage(keyword, (dto.getPageNum() - 1) * 10, dto.getPageSize(), dto.getCategoryId());

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Article::getTitle, keyword);
        articleDTO.setCount(this.count(queryWrapper));
        articleDTO.setArticleVOS(articleVOS);

        return articleDTO;
    }

    @Override
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
    public void deleteById(Long id) {
        articleTagService.deleteByArticleId(id);
        this.removeById(id);
    }

    @Override
    public ArticleDTO getByPageAdmin(PageDTO dto) {
        String keyword = dto.getKeyword();
        ArticleDTO articleDTO = new ArticleDTO();

        List<ArticleVO> articleVOS = this.baseMapper.getByPageAdmin(keyword, (dto.getPageNum() - 1) * 10, dto.getPageSize());

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Article::getTitle, keyword);
        articleDTO.setCount(this.count(queryWrapper));

        articleDTO.setArticleVOS(articleVOS);

        return articleDTO;
    }

    @Override
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
        articleDTO.setArticleVO(this.baseMapper.getById(id));
        return articleDTO;
    }

    @Override
    public Article checkAndGetArticle(Long id) {
        Article article = this.getById(id);
        if (article == null) {
            throw new APIException(ArticleErrorEnum.ARTICLE_NOT_EXIST.getValue());
        }
        return article;
    }

    @Override
    public void top(Long id, Integer isTop) {
        if (!ArticleEnum.TOP.getCode().equals(isTop) && !ArticleEnum.NOT_TOP.getCode().equals(isTop)) {
            throw new APIException(ArticleErrorEnum.IS_TOP_ERROR.getValue());
        }
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, id);
        updateWrapper.set(Article::getIsTop, isTop);
        this.update(updateWrapper);
    }

    @Override
    public void featured(Long id, Integer isFeatured) {
        if (!ArticleEnum.FEATURED.getCode().equals(isFeatured) && !ArticleEnum.NOT_FEATURED.getCode().equals(isFeatured)) {
            throw new APIException(ArticleErrorEnum.IS_FEATURED_ERROR.getValue());
        }
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId, id);
        updateWrapper.set(Article::getIsFeatured, isFeatured);
        this.update(updateWrapper);
    }

    @Override
    public ArticleDTO getNew() {
        ArticleDTO articleDTO = new ArticleDTO();

        List<ArticleVO> articleVOS = this.baseMapper.getByPage("", 0, 1, null);
        Set<Long> articleIds = new HashSet<>();
        for (ArticleVO articleVO : articleVOS) {
            articleIds.add(articleVO.getId());
        }

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        articleDTO.setCount(this.count(queryWrapper));
        articleDTO.setArticleTagVOS(articleTagService.getTagListByArticleIds(articleIds));

        articleDTO.setArticleVOS(articleVOS);

        return articleDTO;
    }

    @Override
    public ArticleDTO getNewFeatured() {
        ArticleDTO articleDTO = new ArticleDTO();

        List<ArticleVO> articleVOS = this.baseMapper.getTwoFeature();

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        articleDTO.setCount(this.count(queryWrapper));

        articleDTO.setArticleVOS(articleVOS);

        return articleDTO;
    }

    @Override
    public ArticleDTO allCount() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setCount(this.count());
        return articleDTO;
    }
}
package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.model.dto.articleTag.ArticleTagDTO;
import com.blog.model.dto.articleTag.SaveDTO;
import com.blog.mapper.ArticleTagMapper;
import com.blog.model.vo.ArticleTagVO;
import com.blog.pojo.ArticleTag;
import com.blog.pojo.Tag;
import com.blog.service.ArticleService;
import com.blog.service.ArticleTagService;
import com.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleService articleService;

    @Override
    public ArticleTagDTO getByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, articleId);
        List<ArticleTag> articleTags = this.list(queryWrapper);

        Set<Long> tagIds = articleTags.stream().map(ArticleTag::getTagId).collect(Collectors.toSet());

        ArticleTagDTO articleTagDTO = new ArticleTagDTO();
        if (!tagIds.isEmpty()) {
            LambdaQueryWrapper<Tag> tagLambdaQueryWrapper = new LambdaQueryWrapper<>();
            tagLambdaQueryWrapper.in(Tag::getId, tagIds);
            List<Tag> tags = tagService.list(tagLambdaQueryWrapper);
            articleTagDTO.setTags(tags);
        }
        return articleTagDTO;
    }

    @Override
    public void saveByArticleId(SaveDTO dto) {
        Long articleId = dto.getArticleId();
        Set<Long> tagIds = dto.getTagIds();

        articleService.checkAndGetArticle(articleId);

        this.deleteByArticleId(articleId);

        List<ArticleTag> articleTags = new ArrayList<>();
        for (Long tagId : tagIds) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(articleId);
            articleTag.setTagId(tagId);
            articleTags.add(articleTag);
        }
        this.saveBatch(articleTags);
    }

    @Override
    public void deleteByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, articleId);
        this.remove(queryWrapper);
    }

    @Override
    public void deleteByTagId(Long tagId) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getTagId, tagId);
        this.remove(queryWrapper);
    }

    @Override
    public List<ArticleTagVO> getTagListByArticleIds(Collection<Long> articleIds) {
        if (articleIds.isEmpty()) {
            return new ArrayList<>();
        }
        return this.baseMapper.getTagListByArticleIds(articleIds);
    }
}
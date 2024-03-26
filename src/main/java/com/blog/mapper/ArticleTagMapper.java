package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.model.vo.ArticleTagVO;
import com.blog.pojo.ArticleTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {
    List<ArticleTagVO> getTagListByArticleIds(Collection<Long> articleIds);
}
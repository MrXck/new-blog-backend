package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.model.vo.ArticleVO;
import com.blog.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleVO> getByPage(String keyword, Integer pageNum, Integer pageSize, Long categoryId);

    List<ArticleVO> getTwoFeature();

    List<ArticleVO> getByPageAdmin(String keyword, Integer pageNum, Integer pageSize);
}
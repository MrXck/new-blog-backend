package com.blog.model.vo;

import com.blog.pojo.Article;
import lombok.Data;

import java.util.List;

@Data
public class ArticleTagVO {

    private Long articleId;
    private String tagName;
    private List<Article> articles;
    private Integer count;
}

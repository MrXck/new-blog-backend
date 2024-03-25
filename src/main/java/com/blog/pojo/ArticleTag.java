package com.blog.pojo;

import lombok.Data;

@Data
public class ArticleTag {

    /**
     * id
     */
    private Long id;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 标签id
     */
    private Long tagId;
}

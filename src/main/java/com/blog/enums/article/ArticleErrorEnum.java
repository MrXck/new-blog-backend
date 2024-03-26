package com.blog.enums.article;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArticleErrorEnum {


    ARTICLE_NOT_EXIST(10001, "文章不存在"),


    IS_TOP_ERROR(10002, "置顶状态只能为 0 或 1"),
    IS_FEATURED_ERROR(10002, "推荐状态只能为 0 或 1");

    private final Integer code;
    private final String value;
}

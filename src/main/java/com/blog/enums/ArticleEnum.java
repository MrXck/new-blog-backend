package com.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArticleEnum {

    /**
     * 状态
     */
    PUBLICITY(1, "公开"),
    PRIVACY(2, "私密"),
    DRAFT(3, "草稿"),

    /**
     * 置顶状态
     */
    TOP(1, "置顶"),
    NOT_TOP(0, "未置顶"),

    /**
     * 删除
     */
    DELETE(1, "删除"),
    NOT_DELETE(0, "未删除"),

    /**
     * 类型
     */
    ORIGIN(1, "原创"),
    RESHIP(2, "转载"),
    TRANSLATE(3, "翻译");


    private final Integer code;
    private final String message;
}

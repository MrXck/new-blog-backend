package com.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryEnum {

    /**
     * 分类
     */
    ALREADY_EXIST_ERROR(0, "分类已存在");

    private final Integer code;
    private final String value;
}

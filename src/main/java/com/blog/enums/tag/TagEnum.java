package com.blog.enums.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TagEnum {

    /**
     * 标签
     */
    ALREADY_EXIST_ERROR(0, "标签已存在");

    private final Integer code;
    private final String value;
}

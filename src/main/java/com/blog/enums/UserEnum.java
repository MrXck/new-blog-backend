package com.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserEnum {
    /**
     * 用户状态
     */
    DISABLE(1, "禁用"),
    NORMAL(0, "正常");

    private final Integer code;
    private final String message;
}

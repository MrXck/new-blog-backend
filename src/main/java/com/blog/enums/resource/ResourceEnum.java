package com.blog.enums.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResourceEnum {

    IS_ANONYMOUS(1, "匿名"),
    IS_NOT_ANONYMOUS(0, "非匿名");

    private final Integer code;
    private final String message;
}

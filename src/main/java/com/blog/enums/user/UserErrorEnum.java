package com.blog.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorEnum {

    DISABLE_USER_ERROR(10010, "禁用状态只能为 0 1");

    private final Integer code;
    private final String value;
}

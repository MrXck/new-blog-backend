package com.blog.enums.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleErrorEnum {

    IS_DISABLE_ERROR(10002, "禁用状态只能为 0 或 1");

    private final Integer code;
    private final String value;
}

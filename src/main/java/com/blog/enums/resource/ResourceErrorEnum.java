package com.blog.enums.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResourceErrorEnum {

    CREATE_RESOURCE_ERROR(10004, "资源名称已存在"),
    PARENT_RESOURCE_ERROR(10005, "不存在该模块"),
    ANONYMOUS_ERROR(10006, "匿名类型错误"),
    NOT_FOUND_PARENT_ERROR(10007, "不存在该模块");

    private final Integer code;
    private final String value;
}

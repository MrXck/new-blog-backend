package com.blog.enums.userRole;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleEnum {

    DELETE_ROLE_ERROR(10010, "该角色已被用户绑定，无法删除");

    private final Integer code;
    private final String value;
}

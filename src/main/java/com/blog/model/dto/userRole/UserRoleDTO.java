package com.blog.model.dto.userRole;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class UserRoleDTO {

    private Page<UserRole> page;

    private UserRole userRole;

    private List<UserRole> userRoles;

}
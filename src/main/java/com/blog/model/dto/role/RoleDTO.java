package com.blog.model.dto.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.Role;
import lombok.Data;

import java.util.List;

@Data
public class RoleDTO {

    private Page<Role> page;

    private Role role;

    private List<Role> roles;

}
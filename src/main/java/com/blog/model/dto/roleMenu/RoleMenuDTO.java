package com.blog.model.dto.roleMenu;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.RoleMenu;
import lombok.Data;

import java.util.List;

@Data
public class RoleMenuDTO {

    private Page<RoleMenu> page;

    private RoleMenu roleMenu;

    private List<RoleMenu> roleMenus;

}
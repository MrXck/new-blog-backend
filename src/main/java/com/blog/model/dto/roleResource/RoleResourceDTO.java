package com.blog.model.dto.roleResource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.RoleResource;
import lombok.Data;

import java.util.List;

@Data
public class RoleResourceDTO {

    private Page<RoleResource> page;

    private RoleResource roleResource;

    private List<RoleResource> roleResources;

}
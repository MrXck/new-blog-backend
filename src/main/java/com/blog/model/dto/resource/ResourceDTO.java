package com.blog.model.dto.resource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.Resource;
import lombok.Data;

import java.util.List;

@Data
public class ResourceDTO {

    private Page<Resource> page;

    private Resource resource;

    private List<Resource> resources;

}
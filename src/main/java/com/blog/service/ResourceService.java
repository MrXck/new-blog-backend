package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.resource.AddResourceDTO;
import com.blog.model.dto.resource.AddResourceParentDTO;
import com.blog.model.dto.resource.ResourceDTO;
import com.blog.model.dto.resource.UpdateDTO;
import com.blog.pojo.Resource;

public interface ResourceService extends IService<Resource> {

    ResourceDTO getByPage(PageDTO dto);

    void add(AddResourceDTO dto);

    void deleteById(Long id);

    void edit(UpdateDTO dto);

    ResourceDTO findById(Long id);

    ResourceDTO all();

    void addResourceParent(AddResourceParentDTO dto);
}
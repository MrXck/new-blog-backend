package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.tag.AddDTO;
import com.blog.model.dto.tag.TagDTO;
import com.blog.model.dto.tag.UpdateDTO;
import com.blog.pojo.Tag;

public interface TagService extends IService<Tag> {
    TagDTO getByPage(PageDTO dto);

    void add(AddDTO dto);

    void delete(Long id);

    void edit(UpdateDTO dto);

    TagDTO all();

    TagDTO allCount();
}
package com.blog.model.dto.tag;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.Tag;
import lombok.Data;

import java.util.List;

@Data
public class TagDTO {

    private Page<Tag> page;

    private List<Tag> tags;

    private Integer count;
}

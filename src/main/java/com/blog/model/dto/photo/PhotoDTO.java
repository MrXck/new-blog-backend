package com.blog.model.dto.photo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.Photo;
import lombok.Data;

import java.util.List;

@Data
public class PhotoDTO {

    private Page<Photo> page;

    private Photo photo;

    private List<Photo> photos;

}
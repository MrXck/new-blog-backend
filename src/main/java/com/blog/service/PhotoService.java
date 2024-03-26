package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.photo.PhotoDTO;
import com.blog.pojo.Photo;

public interface PhotoService extends IService<Photo> {

    PhotoDTO getByPage(PageDTO dto);

    void deleteById(Long id);

    PhotoDTO findById(Long id);

    PhotoDTO all();

    void add(String path);
}
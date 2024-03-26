package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.photo.AddDTO;
import com.blog.model.dto.photo.PhotoDTO;
import com.blog.model.dto.photo.UpdateDTO;
import com.blog.pojo.Photo;

public interface PhotoService extends IService<Photo> {

    PhotoDTO getByPage(PageDTO dto);

    void add(AddDTO dto);

    void deleteById(Long id);

    void edit(UpdateDTO dto);

    PhotoDTO findById(Long id);

    PhotoDTO all();

}
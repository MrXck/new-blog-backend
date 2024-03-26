package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.photoAlbum.AddDTO;
import com.blog.model.dto.photoAlbum.PhotoAlbumDTO;
import com.blog.model.dto.photoAlbum.UpdateDTO;
import com.blog.pojo.PhotoAlbum;

public interface PhotoAlbumService extends IService<PhotoAlbum> {

    PhotoAlbumDTO getByPage(PageDTO dto);

    void add(AddDTO dto);

    void deleteById(Long id);

    void edit(UpdateDTO dto);

    PhotoAlbumDTO findById(Long id);

    PhotoAlbumDTO all();

}
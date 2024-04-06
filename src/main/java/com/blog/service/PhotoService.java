package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.photo.DeleteByPhotoAlbumDTO;
import com.blog.model.dto.photo.PhotoDTO;
import com.blog.model.dto.photo.UpdateByPhotoAlbumDTO;
import com.blog.pojo.Photo;

public interface PhotoService extends IService<Photo> {

    PhotoDTO getByPage(PageDTO dto);

    void deleteById(Long id);

    PhotoDTO findById(Long id);

    PhotoDTO all();

    Long add(String name, String path);

    void updateByPhotoAlbum(UpdateByPhotoAlbumDTO dto);

    PhotoDTO pageByPhotoAlbum(PageDTO dto, Long photoAlbumId);

    void deleteByPhotoAlbum(DeleteByPhotoAlbumDTO dto);
}
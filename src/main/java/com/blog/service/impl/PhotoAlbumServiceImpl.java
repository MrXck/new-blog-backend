package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.PhotoAlbumMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.photoAlbum.AddDTO;
import com.blog.model.dto.photoAlbum.PhotoAlbumDTO;
import com.blog.model.dto.photoAlbum.UpdateDTO;
import com.blog.pojo.PhotoAlbum;
import com.blog.service.PhotoAlbumService;
import com.blog.service.PhotoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoAlbumServiceImpl extends ServiceImpl<PhotoAlbumMapper, PhotoAlbum> implements PhotoAlbumService {

    @Autowired
    private PhotoService photoService;

    @Override
    public PhotoAlbumDTO getByPage(PageDTO dto) {
        PhotoAlbumDTO photoAlbumDTO = new PhotoAlbumDTO();
        photoAlbumDTO.setPhotoAlbumVOS(this.baseMapper.getByPage((dto.getPageNum() - 1) * 10, dto.getPageSize()));
        photoAlbumDTO.setCount(this.count());
        return photoAlbumDTO;
    }

    @Override
    public void add(AddDTO dto) {
        PhotoAlbum photoAlbum = new PhotoAlbum();
        BeanUtils.copyProperties(dto, photoAlbum);
        this.save(photoAlbum);
    }

    @Override
    public void deleteById(Long id) {
        photoService.deleteByPhotoAlbumId(id);
        this.removeById(id);
    }

    @Override
    public void edit(UpdateDTO dto) {
        PhotoAlbum photoAlbum = new PhotoAlbum();
        BeanUtils.copyProperties(dto, photoAlbum);
        this.updateById(photoAlbum);
    }

    @Override
    public PhotoAlbumDTO findById(Long id) {
        PhotoAlbumDTO photoAlbumDTO = new PhotoAlbumDTO();
        photoAlbumDTO.setPhotoAlbum(this.getById(id));
        return photoAlbumDTO;
    }

    @Override
    public PhotoAlbumDTO all() {
        PhotoAlbumDTO photoAlbumDTO = new PhotoAlbumDTO();
        photoAlbumDTO.setPhotoAlbums(this.list());
        return photoAlbumDTO;
    }

}
package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.PhotoAlbumMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.photoAlbum.AddDTO;
import com.blog.model.dto.photoAlbum.PhotoAlbumDTO;
import com.blog.model.dto.photoAlbum.UpdateDTO;
import com.blog.pojo.PhotoAlbum;
import com.blog.service.PhotoAlbumService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhotoAlbumServiceImpl extends ServiceImpl<PhotoAlbumMapper, PhotoAlbum> implements PhotoAlbumService {

    @Override
    public PhotoAlbumDTO getByPage(PageDTO dto) {
        Page<PhotoAlbum> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        String keyword = dto.getKeyword();
        LambdaQueryWrapper<PhotoAlbum> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(PhotoAlbum::getName, keyword);
        queryWrapper.orderByDesc(PhotoAlbum::getUpdateTime);
        queryWrapper.orderByDesc(PhotoAlbum::getCreateTime);
        PhotoAlbumDTO photoAlbumDTO = new PhotoAlbumDTO();
        photoAlbumDTO.setPage(this.page(page, queryWrapper));
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
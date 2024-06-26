package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.PhotoMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.photo.DeleteByPhotoAlbumDTO;
import com.blog.model.dto.photo.PhotoDTO;
import com.blog.model.dto.photo.UpdateByPhotoAlbumDTO;
import com.blog.pojo.Photo;
import com.blog.service.PhotoService;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {

    @Override
    public PhotoDTO getByPage(PageDTO dto) {
        Page<Photo> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        String keyword = dto.getKeyword();
        LambdaQueryWrapper<Photo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Photo::getName, keyword);
        queryWrapper.isNull(Photo::getPhotoAlbumId);
        queryWrapper.orderByDesc(Photo::getCreateTime);
        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setPage(this.page(page, queryWrapper));
        return photoDTO;
    }

    @Override
    public void deleteById(Long id) {
        this.removeById(id);
    }

    @Override
    public PhotoDTO findById(Long id) {
        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setPhoto(this.getById(id));
        return photoDTO;
    }

    @Override
    public PhotoDTO all() {
        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setPhotos(this.list());
        return photoDTO;
    }

    @Override
    public Long add(String name, String path) {
        Photo photo = new Photo();
        photo.setName(name);
        photo.setPath(path);
        photo.setSrc(path);
        this.save(photo);
        return photo.getId();
    }

    @Override
    public void updateByPhotoAlbum(UpdateByPhotoAlbumDTO dto) {
        if (dto.getPhotoIds().isEmpty()) {
            return;
        }

        LambdaUpdateWrapper<Photo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Photo::getPhotoAlbumId, dto.getPhotoAlbumId());
        updateWrapper.in(Photo::getId, dto.getPhotoIds());
        this.update(updateWrapper);
    }

    @Override
    public PhotoDTO pageByPhotoAlbum(PageDTO dto, Long photoAlbumId) {
        Page<Photo> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        LambdaQueryWrapper<Photo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Photo::getPhotoAlbumId, photoAlbumId);

        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setPage(this.page(page, queryWrapper));

        return photoDTO;
    }

    @Override
    public void deleteByPhotoAlbum(DeleteByPhotoAlbumDTO dto) {
        if (dto.getPhotoIds().isEmpty()) {
            return;
        }

        LambdaQueryWrapper<Photo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Photo::getPhotoAlbumId, dto.getPhotoAlbumId());
        queryWrapper.in(Photo::getId, dto.getPhotoIds());
        this.remove(queryWrapper);
    }

    @Override
    public void deleteByPhotoAlbumId(Long id) {
        LambdaQueryWrapper<Photo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Photo::getPhotoAlbumId, id);
        this.remove(queryWrapper);
    }

}
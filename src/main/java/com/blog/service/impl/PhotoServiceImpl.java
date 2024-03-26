package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.PhotoMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.photo.AddDTO;
import com.blog.model.dto.photo.PhotoDTO;
import com.blog.model.dto.photo.UpdateDTO;
import com.blog.pojo.Photo;
import com.blog.service.PhotoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {

    @Override
    public PhotoDTO getByPage(PageDTO dto) {
        Page<Photo> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        String keyword = dto.getKeyword();
        LambdaQueryWrapper<Photo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Photo::getCreateTime);
        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setPage(this.page(page, queryWrapper));
        return photoDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AddDTO dto) {
        Photo photo = new Photo();
        BeanUtils.copyProperties(dto, photo);
        this.save(photo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(UpdateDTO dto) {
        Photo photo = new Photo();
        BeanUtils.copyProperties(dto, photo);
        this.updateById(photo);
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

}
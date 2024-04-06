package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.photo.PhotoDTO;
import com.blog.model.dto.photo.UpdateByPhotoAlbumDTO;
import com.blog.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Photo相关")
@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @ApiOperation("分页获取Photo")
    @PostMapping("/page")
    public PhotoDTO page(@RequestBody @Valid PageDTO dto) {
        return photoService.getByPage(dto);
    }

    @ApiOperation("删除Photo")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        photoService.deleteById(id);
    }

    @ApiOperation("获取所有Photo")
    @GetMapping("/")
    public PhotoDTO list() {
        return photoService.all();
    }

    @ApiOperation("根据id获取Photo")
    @GetMapping("/{id}")
    public PhotoDTO getById(@PathVariable("id") Long id) {
        return photoService.findById(id);
    }

    @ApiOperation("根据相册id修改图片所属相册")
    @PostMapping("/updateByPhotoAlbum")
    public void updateByPhotoAlbum(@RequestBody @Valid UpdateByPhotoAlbumDTO dto) {
        photoService.updateByPhotoAlbum(dto);
    }

    @ApiOperation("根据相册id分页获取该相册的图片")
    @PostMapping("/pageByPhotoAlbum/{photoAlbumId}")
    public PhotoDTO pageByPhotoAlbum(@RequestBody @Valid PageDTO dto, @PathVariable("photoAlbumId") Long photoAlbumId) {
        return photoService.pageByPhotoAlbum(dto, photoAlbumId);
    }

}
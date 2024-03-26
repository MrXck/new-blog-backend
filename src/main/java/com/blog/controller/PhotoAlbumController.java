package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.photoAlbum.AddDTO;
import com.blog.model.dto.photoAlbum.PhotoAlbumDTO;
import com.blog.model.dto.photoAlbum.UpdateDTO;
import com.blog.service.PhotoAlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "PhotoAlbum相关")
@RestController
@RequestMapping("/photoAlbum")
public class PhotoAlbumController {

    @Autowired
    private PhotoAlbumService photoAlbumService;

    @ApiOperation("分页获取PhotoAlbum")
    @PostMapping("/page")
    public PhotoAlbumDTO page(@RequestBody @Valid PageDTO dto) {
        return photoAlbumService.getByPage(dto);
    }

    @ApiOperation("添加PhotoAlbum")
    @PostMapping("/add")
    public void add(@RequestBody @Valid AddDTO dto) {
        photoAlbumService.add(dto);
    }

    @ApiOperation("删除PhotoAlbum")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        photoAlbumService.deleteById(id);
    }

    @ApiOperation("修改PhotoAlbum")
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateDTO dto) {
        photoAlbumService.edit(dto);
    }

    @ApiOperation("获取所有PhotoAlbum")
    @GetMapping("/")
    public PhotoAlbumDTO list() {
        return photoAlbumService.all();
    }

    @ApiOperation("根据id获取PhotoAlbum")
    @GetMapping("/{id}")
    public PhotoAlbumDTO getById(@PathVariable("id") Long id) {
        return photoAlbumService.findById(id);
    }

}
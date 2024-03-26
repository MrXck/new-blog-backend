package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.photo.AddDTO;
import com.blog.model.dto.photo.PhotoDTO;
import com.blog.model.dto.photo.UpdateDTO;
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

    @ApiOperation("添加Photo")
    @PostMapping("/add")
    public void add(@RequestBody @Valid AddDTO dto) {
        photoService.add(dto);
    }

    @ApiOperation("删除Photo")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        photoService.deleteById(id);
    }

    @ApiOperation("修改Photo")
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateDTO dto) {
        photoService.edit(dto);
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

}
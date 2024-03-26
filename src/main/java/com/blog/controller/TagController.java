package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.tag.AddDTO;
import com.blog.model.dto.tag.TagDTO;
import com.blog.model.dto.tag.UpdateDTO;
import com.blog.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "标签相关")
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation("分页获取标签")
    @PostMapping("/page")
    public TagDTO page(@RequestBody @Valid PageDTO dto) {
        return tagService.getByPage(dto);
    }

    @ApiOperation("添加标签")
    @PostMapping("/add")
    public void add(@RequestBody @Valid AddDTO dto) {
        tagService.add(dto);
    }

    @ApiOperation("删除标签")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        tagService.delete(id);
    }

    @ApiOperation("修改标签")
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateDTO dto) {
        tagService.edit(dto);
    }

    @ApiOperation("获取所有标签")
    @GetMapping("/all")
    public TagDTO all() {
        return tagService.all();
    }
}
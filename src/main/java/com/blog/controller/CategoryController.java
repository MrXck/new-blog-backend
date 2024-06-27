package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.category.AddDTO;
import com.blog.model.dto.category.CategoryDTO;
import com.blog.model.dto.category.UpdateDTO;
import com.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "分类管理")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("分页获取分类")
    @PostMapping("/page")
    public CategoryDTO page(@RequestBody @Valid PageDTO dto) {
        return categoryService.getByPage(dto);
    }

    @ApiOperation("添加分类")
    @PostMapping("/insert")
    public void insert(@RequestBody @Valid AddDTO dto) {
        categoryService.add(dto);
    }

    @ApiOperation("删除分类")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
    }

    @ApiOperation("修改分类")
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateDTO dto) {
        categoryService.edit(dto);
    }

    @ApiOperation("获取所有分类")
    @GetMapping("/all")
    public CategoryDTO all() {
        return categoryService.all();
    }

    @ApiOperation("获取分类总数")
    @GetMapping("/count")
    public CategoryDTO count() {
        return categoryService.allCount();
    }

}
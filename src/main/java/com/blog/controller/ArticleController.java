package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.article.AddDTO;
import com.blog.model.dto.article.ArticleDTO;
import com.blog.model.dto.article.UpdateDTO;
import com.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "文章相关")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation("分页获取文章列表")
    @PostMapping("/page")
    public ArticleDTO page(@RequestBody @Valid PageDTO dto) {
        return articleService.getByPage(dto);
    }

    @ApiOperation("管理后台分页获取文章列表")
    @PostMapping("/admin/page")
    public ArticleDTO adminPage(@RequestBody @Valid PageDTO dto) {
        return articleService.getByPageAdmin(dto);
    }

    @ApiOperation("新增文章")
    @PostMapping("/insert")
    public ArticleDTO insert(@RequestBody @Valid AddDTO dto) {
        return articleService.add(dto);
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        articleService.deleteById(id);
    }

    @ApiOperation("修改文章")
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateDTO dto) {
        articleService.edit(dto);
    }

    @ApiOperation("根据id获取文章")
    @GetMapping("/{id}")
    public ArticleDTO findById(@PathVariable("id") Long id) {
        return articleService.findById(id);
    }

    @ApiOperation("根据id修改文章置顶状态")
    @GetMapping("/top/{id}/{isTop}")
    public void updateTop(@PathVariable("id") Long id, @PathVariable("isTop") Integer isTop) {
        articleService.top(id, isTop);
    }

    @ApiOperation("根据id修改文章推荐状态")
    @GetMapping("/featured/{id}/{isFeatured}")
    public void updateFeatured(@PathVariable("id") Long id, @PathVariable("isFeatured") Integer isFeatured) {
        articleService.featured(id, isFeatured);
    }

    @ApiOperation("获取最新的一篇文章")
    @GetMapping("/getNew")
    public ArticleDTO getNew() {
        return articleService.getNew();
    }

}
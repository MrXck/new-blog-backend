package com.blog.controller;

import com.blog.dto.articleTag.ArticleTagDTO;
import com.blog.dto.articleTag.SaveDTO;
import com.blog.service.ArticleTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "文章标签相关")
@RestController
@RequestMapping("/articleTag")
public class ArticleTagController {

    @Autowired
    private ArticleTagService articletagService;

    @ApiOperation("获取指定文章的所有标签")
    @GetMapping("/{id}")
    public ArticleTagDTO getByArticleId(@PathVariable("id") Long articleId) {
        return articletagService.getByArticleId(articleId);
    }

    @ApiOperation("根据文章id保存标签")
    @PostMapping("/save")
    public void save(@RequestBody @Valid SaveDTO dto) {
        articletagService.saveByArticleId(dto);
    }

    @ApiOperation("根据文章id删除标签")
    @DeleteMapping("/deleteByArticleId/{id}")
    public void deleteByArticleId(@PathVariable("id") Long articleId) {
        articletagService.deleteByArticleId(articleId);
    }

    @ApiOperation("根据标签id删除标签")
    @DeleteMapping("/deleteByTagId/{id}")
    public void deleteByTagId(@PathVariable("id") Long tagId) {
        articletagService.deleteByTagId(tagId);
    }
}
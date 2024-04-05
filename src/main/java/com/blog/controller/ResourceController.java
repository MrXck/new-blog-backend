package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.resource.*;
import com.blog.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Resource相关")
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation("分页获取Resource")
    @PostMapping("/page")
    public ResourceDTO page(@RequestBody @Valid PageDTO dto) {
        return resourceService.getByPage(dto);
    }

    @ApiOperation("添加Resource")
    @PostMapping("/insertResource")
    public void insertResource(@RequestBody @Valid AddResourceDTO dto) {
        resourceService.add(dto);
    }

    @ApiOperation("添加Resource模块")
    @PostMapping("/insertResourceParent")
    public void insertResourceParent(@RequestBody @Valid AddResourceParentDTO dto) {
        resourceService.addResourceParent(dto);
    }

    @ApiOperation("删除Resource")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        resourceService.deleteById(id);
    }

    @ApiOperation("删除Resource模块")
    @DeleteMapping("/parent/{id}")
    public void deleteParent(@PathVariable("id") Long id) {
        resourceService.deleteParentById(id);
    }

    @ApiOperation("修改Resource")
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateDTO dto) {
        resourceService.edit(dto);
    }

    @ApiOperation("修改Resource模块")
    @PutMapping("/updateParent")
    public void updateParent(@RequestBody @Valid UpdateParentDTO dto) {
        resourceService.editParent(dto);
    }

    @ApiOperation("获取所有Resource")
    @GetMapping("/")
    public ResourceDTO list() {
        return resourceService.all();
    }

    @ApiOperation("根据id获取Resource")
    @GetMapping("/{id}")
    public ResourceDTO getById(@PathVariable("id") Long id) {
        return resourceService.findById(id);
    }

    @ApiOperation("修改resource的匿名状态")
    @GetMapping("/anonymous/{id}/{anonymous}")
    public void updateAnonymous(@PathVariable("id") Long id, @PathVariable("anonymous") Integer anonymous) {
        resourceService.updateAnonymous(id, anonymous);
    }

}
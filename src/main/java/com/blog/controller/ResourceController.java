package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.resource.AddResourceDTO;
import com.blog.model.dto.resource.AddResourceParentDTO;
import com.blog.model.dto.resource.ResourceDTO;
import com.blog.model.dto.resource.UpdateDTO;
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
    @PostMapping("/addResource")
    public void addResource(@RequestBody @Valid AddResourceDTO dto) {
        resourceService.add(dto);
    }

    @ApiOperation("添加Resource模块")
    @PostMapping("/addResourceParent")
    public void addResourceParent(@RequestBody @Valid AddResourceParentDTO dto) {
        resourceService.addResourceParent(dto);
    }

    @ApiOperation("删除Resource")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        resourceService.deleteById(id);
    }

    @ApiOperation("修改Resource")
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateDTO dto) {
        resourceService.edit(dto);
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

}
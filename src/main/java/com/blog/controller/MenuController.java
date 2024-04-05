package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.menu.AddDTO;
import com.blog.model.dto.menu.MenuDTO;
import com.blog.model.dto.menu.UpdateDTO;
import com.blog.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Menu相关")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("分页获取Menu")
    @PostMapping("/page")
    public MenuDTO page(@RequestBody @Valid PageDTO dto) {
        return menuService.getByPage(dto);
    }

    @ApiOperation("添加Menu")
    @PostMapping("/insert")
    public void insert(@RequestBody @Valid AddDTO dto) {
        menuService.add(dto);
    }

    @ApiOperation("删除Menu")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        menuService.deleteById(id);
    }

    @ApiOperation("修改Menu")
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateDTO dto) {
        menuService.edit(dto);
    }

    @ApiOperation("获取所有Menu")
    @GetMapping("/")
    public MenuDTO list() {
        return menuService.all();
    }

    @ApiOperation("根据id获取Menu")
    @GetMapping("/{id}")
    public MenuDTO getById(@PathVariable("id") Long id) {
        return menuService.findById(id);
    }

}
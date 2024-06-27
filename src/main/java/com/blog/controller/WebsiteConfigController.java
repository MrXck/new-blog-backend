package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.websiteConfig.InsertDTO;
import com.blog.model.dto.websiteConfig.UpdateDTO;
import com.blog.model.dto.websiteConfig.WebsiteConfigDTO;
import com.blog.service.WebsiteConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/websiteConfig")
public class WebsiteConfigController {

    @Autowired
    private WebsiteConfigService websiteConfigService;

    @ApiOperation("分页获取WebsiteConfig")
    @PostMapping("/page")
    public WebsiteConfigDTO page(@RequestBody @Valid PageDTO dto) {
        return websiteConfigService.getByPage(dto);
    }

    @ApiOperation("添加WebsiteConfig")
    @PostMapping("/insert")
    public void insert(@RequestBody @Valid InsertDTO dto) {
        websiteConfigService.insert(dto);
    }

    @ApiOperation("修改WebsiteConfig")
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateDTO dto) {
        websiteConfigService.edit(dto);
    }

}
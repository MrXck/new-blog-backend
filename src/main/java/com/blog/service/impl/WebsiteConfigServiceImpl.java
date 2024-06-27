package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.WebsiteConfigMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.websiteConfig.InsertDTO;
import com.blog.model.dto.websiteConfig.UpdateDTO;
import com.blog.model.dto.websiteConfig.WebsiteConfigDTO;
import com.blog.pojo.WebsiteConfig;
import com.blog.service.WebsiteConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class WebsiteConfigServiceImpl extends ServiceImpl<WebsiteConfigMapper, WebsiteConfig> implements WebsiteConfigService {

    @Override
    public WebsiteConfigDTO getByPage(PageDTO dto) {
        Page<WebsiteConfig> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<WebsiteConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(WebsiteConfig::getUpdateTime);
        queryWrapper.orderByDesc(WebsiteConfig::getCreateTime);
        WebsiteConfigDTO websiteConfigDTO = new WebsiteConfigDTO();
        websiteConfigDTO.setPage(this.page(page, queryWrapper));
        return websiteConfigDTO;
    }

    @Override
    public void insert(InsertDTO dto) {
        WebsiteConfig websiteConfig = new WebsiteConfig();
        BeanUtils.copyProperties(dto, websiteConfig);
        if (this.count() == 0) {
            this.save(websiteConfig);
        }
    }

    @Override
    public void edit(UpdateDTO dto) {
        WebsiteConfig websiteConfig = new WebsiteConfig();
        BeanUtils.copyProperties(dto, websiteConfig);
        this.updateById(websiteConfig);
    }

}
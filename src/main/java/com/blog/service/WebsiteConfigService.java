package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.websiteConfig.InsertDTO;
import com.blog.model.dto.websiteConfig.UpdateDTO;
import com.blog.model.dto.websiteConfig.WebsiteConfigDTO;
import com.blog.pojo.WebsiteConfig;

public interface WebsiteConfigService extends IService<WebsiteConfig> {

    WebsiteConfigDTO getByPage(PageDTO dto);

    void insert(InsertDTO dto);

    void edit(UpdateDTO dto);

}
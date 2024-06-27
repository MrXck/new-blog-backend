package com.blog.model.dto.websiteConfig;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.WebsiteConfig;
import lombok.Data;

import java.util.List;

@Data
public class WebsiteConfigDTO {

    private Page<WebsiteConfig> page;

    private WebsiteConfig websiteConfig;

    private List<WebsiteConfig> websiteConfigs;

}
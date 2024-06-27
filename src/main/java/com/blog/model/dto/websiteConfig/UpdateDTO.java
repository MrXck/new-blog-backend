package com.blog.model.dto.websiteConfig;

import com.blog.pojo.WebsiteConfig;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UpdateDTO extends WebsiteConfig {

    @NotNull(message = "id不能为空")
    private Long id;

    private String authorCover;

    private String websiteLogo;

    private String authorName;

    private String authorDesc;

    private LocalDate websiteCreateTime;

    private String websiteAnnouncement;

    private String githubUrl;

    private String giteeUrl;

    private String qqUrl;

    private String wechatUrl;

    private String wbUrl;

    private String csdnUrl;

    private String zhihuUrl;

    private String juejinUrl;

    private String twitterUrl;

    private String stackoverflowUrl;

}
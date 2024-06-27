package com.blog.model.dto.websiteConfig;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InsertDTO {

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
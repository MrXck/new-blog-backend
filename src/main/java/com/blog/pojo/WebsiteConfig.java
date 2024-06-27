package com.blog.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class WebsiteConfig {

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

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

}

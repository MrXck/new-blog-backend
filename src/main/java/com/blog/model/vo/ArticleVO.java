package com.blog.model.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleVO {

    private Long id;
    private String title;
    private String cover;
    private String digest;
    private String username;
    private String categoryName;
    private Integer isTop;
    private Integer isFeatured;
    private Integer type;
    private Integer status;
    private LocalDateTime createTime;
    private List<String> tagList;
}

package com.blog.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PhotoAlbumVO {

    private Long id;

    private String name;

    private String intro;

    private String cover;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer photoCount;
}

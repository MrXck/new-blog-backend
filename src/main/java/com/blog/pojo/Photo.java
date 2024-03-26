package com.blog.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Photo {

    private Long id;

    private String src;

    private String name;

    private Long photoAlbumId;

    private String path;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}

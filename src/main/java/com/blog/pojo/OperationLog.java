package com.blog.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperationLog {

    private Long id;

    private String method;

    private String path;

    private String param;

    private String response;

    private Long userId;

    private String nickname;

    private String ipAddress;

    private String ipSource;

    private Long consuming;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

}

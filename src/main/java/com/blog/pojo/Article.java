package com.blog.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article {

    private Long id;

    /**
     * 作者id
     */
    private Long userId;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 封面
     */
    private String cover;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String digest;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否置顶 0否 1是
     */
    private Integer isTop;

    /**
     * 是否推荐  0否 1是
     */
    private Integer isFeatured;

    /**
     * 是否删除  0否 1是
     */
    private Integer isDelete;

    /**
     * 状态 1公开 2私密 3草稿
     */
    private Integer status;

    /**
     * 文章类型 1原创 2转载 3翻译
     */
    private Integer type;

    /**
     * 访问密码
     */
    private String password;

    /**
     * 原文链接
     */
    private String originalUrl;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}

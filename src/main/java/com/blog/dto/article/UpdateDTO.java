package com.blog.dto.article;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateDTO {

    @NotNull(message = "文章id不能为空")
    private Long id;

    @NotNull(message = "分类id不能为空")
    private Long categoryId;

    @NotNull(message = "封面不能为空")
    private String cover;

    @NotNull(message = "标题不能为空")
    private String title;

    private String digest;

    @NotBlank(message = "内容不能为空")
    private String content;

    @NotNull(message = "是否置顶不能为空")
    @Min(value = 0, message = "是否置顶必须为0或1")
    @Max(value = 1, message = "是否置顶必须为0或1")
    private Integer isTop;

    @NotNull(message = "是否推荐不能为空")
    @Min(value = 0, message = "是否推荐必须为0或1")
    @Max(value = 1, message = "是否推荐必须为0或1")
    private Integer isFeatured;

    @NotNull(message = "状态不能为空")
    @Min(value = 1, message = "状态必须为1-3")
    @Max(value = 3, message = "状态必须为1-3")
    private Integer status;

    @NotNull(message = "类型不能为空")
    @Min(value = 1, message = "类型必须为1-3")
    @Max(value = 3, message = "类型必须为1-3")
    private Integer type;

    private String password;

    private String originalUrl;
}

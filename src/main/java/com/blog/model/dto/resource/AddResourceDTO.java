package com.blog.model.dto.resource;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddResourceDTO {

    @NotEmpty(message = "资源名称不能为空")
    private String name;

    @NotEmpty(message = "路径不能为空")
    private String path;

    @NotEmpty(message = "请求方式不能为空")
    private String method;

    @NotNull(message = "是否匿名不能为空")
    private Integer isAnonymous;

    @NotNull(message = "父模块不能为空")
    private Long parentId;
}
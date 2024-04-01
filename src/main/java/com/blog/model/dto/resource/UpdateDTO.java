package com.blog.model.dto.resource;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateDTO {

    @NotNull(message = "id不能为空")
    private Long id;

    @NotEmpty(message = "name不能为空")
    private String name;

    @NotEmpty(message = "path不能为空")
    private String path;

    @NotEmpty(message = "method不能为空")
    private String method;

}
package com.blog.model.dto.category;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateDTO {

    @NotNull(message = "分类id不能为空")
    private Long id;

    @NotEmpty(message = "分类名称不能为空")
    private String name;
}

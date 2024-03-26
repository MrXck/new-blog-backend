package com.blog.model.dto.category;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddDTO {

    @NotEmpty(message = "分类名称不能为空")
    private String name;
}

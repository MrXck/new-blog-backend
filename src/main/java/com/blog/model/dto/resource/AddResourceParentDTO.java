package com.blog.model.dto.resource;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddResourceParentDTO {

    @NotEmpty(message = "资源名称不能为空")
    private String name;

}
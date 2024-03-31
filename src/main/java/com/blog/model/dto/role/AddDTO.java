package com.blog.model.dto.role;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddDTO {

    @NotEmpty(message = "name不能为空")
    private String name;

}
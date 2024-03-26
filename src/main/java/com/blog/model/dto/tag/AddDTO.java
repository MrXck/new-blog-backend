package com.blog.model.dto.tag;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddDTO {

    @NotEmpty(message = "标签名不能为空")
    private String name;
}

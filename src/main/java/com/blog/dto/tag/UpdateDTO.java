package com.blog.dto.tag;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateDTO {

    @NotNull(message = "id不能为空")
    private Long id;

    @NotEmpty(message = "标签名称不能为空")
    private String name;
}

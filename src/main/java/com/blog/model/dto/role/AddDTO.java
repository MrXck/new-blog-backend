package com.blog.model.dto.role;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AddDTO {

    @NotEmpty(message = "name不能为空")
    private String name;

    @NotNull
    private List<Long> resourceIds;

}
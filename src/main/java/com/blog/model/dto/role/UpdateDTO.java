package com.blog.model.dto.role;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateDTO {

    @NotNull(message = "id不能为空")
    private Long id;

    @NotEmpty(message = "name不能为空")
    private String name;

    @NotNull(message = "资源id不能为空")
    private List<Long> resourceIds;

}
package com.blog.model.dto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateDTO {

    @NotNull(message = "id不能为空")
    private Long id;

    @NotEmpty(message = "昵称不能为空")
    private String nickname;

    @NotNull(message = "角色id不能为空")
    private List<Long> roleIds;
}

package com.blog.model.dto.photoAlbum;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateDTO {

    @NotNull(message = "id不能为空")
    private Long id;

    @NotEmpty(message = "相册名不能为空")
    private String name;

    @NotEmpty(message = "相册描述不能为空")
    private String intro;

    @NotEmpty(message = "相册封面不能为空")
    private String cover;

}
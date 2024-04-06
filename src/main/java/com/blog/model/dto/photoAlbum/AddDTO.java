package com.blog.model.dto.photoAlbum;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddDTO {

    @NotEmpty(message = "相册名称不能为空")
    private String name;

    @NotEmpty(message = "相册描述不能为空")
    private String intro;

    @NotEmpty(message = "相册封面不能为空")
    private String cover;

}
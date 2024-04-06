package com.blog.model.dto.photo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateByPhotoAlbumDTO {

    @NotNull(message = "photoAlbumId不能为空")
    private Long photoAlbumId;

    @NotNull
    private List<Long> photoIds;
}

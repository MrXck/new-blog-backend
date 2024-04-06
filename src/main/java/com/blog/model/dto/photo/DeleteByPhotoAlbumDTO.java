package com.blog.model.dto.photo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DeleteByPhotoAlbumDTO {

    @NotNull(message = "photoAlbumId不能为空")
    private Long photoAlbumId;

    @NotNull(message = "photoIds不能为空")
    private List<Long> photoIds;
}

package com.blog.dto.articleTag;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class SaveDTO {

    @NotNull(message = "文章id不能为空")
    private Long articleId;

    @NotNull
    private Set<Long> tagIds;
}

package com.blog.dto.articleTag;

import com.blog.pojo.Tag;
import lombok.Data;

import java.util.List;

@Data
public class ArticleTagDTO {

    private List<Tag> tags;
}

package com.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UploadPathEnum {

    BLOG_COVER("cover/blog/"),
    BLOG_CONTENT_PHOTO("blog/content/"),
    PHOTO_ALBUM_COVER("cover/photoAlbum/"),
    PHOTO("photo/"),
    AVATAR("avatar/");

    private final String path;
}

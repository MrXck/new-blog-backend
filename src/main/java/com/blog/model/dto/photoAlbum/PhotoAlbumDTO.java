package com.blog.model.dto.photoAlbum;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.model.vo.PhotoAlbumVO;
import com.blog.pojo.PhotoAlbum;
import lombok.Data;

import java.util.List;

@Data
public class PhotoAlbumDTO {

    private Page<PhotoAlbum> page;

    private PhotoAlbum photoAlbum;

    private List<PhotoAlbum> photoAlbums;

    private Integer count;

    private List<PhotoAlbumVO> photoAlbumVOS;

}
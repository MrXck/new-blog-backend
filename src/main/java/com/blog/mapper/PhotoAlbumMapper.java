package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.model.vo.PhotoAlbumVO;
import com.blog.pojo.PhotoAlbum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoAlbumMapper extends BaseMapper<PhotoAlbum> {

    List<PhotoAlbumVO> getByPage(Integer pageNum, Integer pageSize);
}
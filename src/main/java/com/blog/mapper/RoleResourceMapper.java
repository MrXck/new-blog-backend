package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.Resource;
import com.blog.pojo.RoleResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleResourceMapper extends BaseMapper<RoleResource> {
    List<Resource> getResourcesByRoleId(Long roleId);
}
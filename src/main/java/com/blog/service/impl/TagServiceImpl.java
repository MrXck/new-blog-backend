package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.dto.PageDTO;
import com.blog.dto.tag.AddDTO;
import com.blog.dto.tag.TagDTO;
import com.blog.dto.tag.UpdateDTO;
import com.blog.enums.TagEnum;
import com.blog.exception.APIException;
import com.blog.mapper.TagMapper;
import com.blog.pojo.Tag;
import com.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Override
    public TagDTO getByPage(PageDTO dto) {
        Page<Tag> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        String keyword = dto.getKeyword();

        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Tag::getName, keyword);
        queryWrapper.orderByDesc(Tag::getUpdateTime);
        queryWrapper.orderByDesc(Tag::getCreateTime);

        TagDTO tagDTO = new TagDTO();
        tagDTO.setPage(this.page(page, queryWrapper));
        return tagDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AddDTO dto) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getName, dto.getName());
        int count = this.count(queryWrapper);
        if (count != 0) {
            throw new APIException(TagEnum.ALREADY_EXIST_ERROR.getValue());
        }

        Tag tag = new Tag();
        tag.setName(dto.getName());
        this.save(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(UpdateDTO dto) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(dto, tag);
        this.updateById(tag);
    }

    @Override
    public TagDTO all() {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setTags(this.list());
        return tagDTO;
    }
}
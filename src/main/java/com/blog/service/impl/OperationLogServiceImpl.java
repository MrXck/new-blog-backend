package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.OperationLogMapper;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.operationLog.OperationLogDTO;
import com.blog.pojo.OperationLog;
import com.blog.service.OperationLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {
    @Override
    public OperationLogDTO getByPage(PageDTO dto) {
        Page<OperationLog> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<OperationLog> queryWrapper = new LambdaQueryWrapper<>();
        String keyword = dto.getKeyword();
        queryWrapper.orderByDesc(OperationLog::getUpdateTime);
        queryWrapper.orderByDesc(OperationLog::getCreateTime);
        queryWrapper.like(OperationLog::getPath, keyword);

        OperationLogDTO operationLogDTO = new OperationLogDTO();
        operationLogDTO.setPage(this.page(page, queryWrapper));
        return operationLogDTO;
    }

    @Override
    public void delete(Long id) {
        this.removeById(id);
    }
}

package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.model.dto.PageDTO;
import com.blog.model.dto.operationLog.OperationLogDTO;
import com.blog.pojo.OperationLog;

public interface OperationLogService extends IService<OperationLog> {
    OperationLogDTO getByPage(PageDTO dto);

    void delete(Long id);
}

package com.blog.model.dto.operationLog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.OperationLog;
import lombok.Data;

@Data
public class OperationLogDTO {

    private Page<OperationLog> page;
}

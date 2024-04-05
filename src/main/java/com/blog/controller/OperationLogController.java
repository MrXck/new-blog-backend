package com.blog.controller;

import com.blog.model.dto.PageDTO;
import com.blog.model.dto.operationLog.OperationLogDTO;
import com.blog.service.OperationLogService;
import com.blog.utils.NoLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "操作日志模块")
@RestController
@RequestMapping("/operationLog")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    @ApiOperation("分页获取操作日志")
    @NoLog
    @PostMapping("/page")
    public OperationLogDTO page(@RequestBody @Valid PageDTO dto) {
        return operationLogService.getByPage(dto);
    }

    @ApiOperation("删除日志")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        operationLogService.delete(id);
    }

}

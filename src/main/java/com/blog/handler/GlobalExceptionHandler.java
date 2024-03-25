package com.blog.handler;

import com.blog.common.R;
import com.blog.exception.APIException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author xck
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R<String> handleBindException(Exception ex) {
        ex.printStackTrace();
        return R.error("操作失败");
    }

    @ExceptionHandler(APIException.class)
    public R<String> handleBindException(APIException ex) {
        // TODO 日志
        return R.error(ex.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public R<String> handleBindException(BindException ex) {
        ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);
        // TODO 日志
        return R.error(objectError.getDefaultMessage());
    }

}

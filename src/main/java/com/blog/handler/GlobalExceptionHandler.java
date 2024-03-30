package com.blog.handler;

import com.blog.common.R;
import com.blog.exception.APIException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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

    @ExceptionHandler(BadCredentialsException.class)
    public R<String> handleBindException(BadCredentialsException ex) {
        // TODO 日志
        return R.error(ex.getMessage());
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public R<String> handleBindException(InternalAuthenticationServiceException ex) {
        // TODO 日志
        return R.error(ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public R<String> handleBindException(AccessDeniedException ex) {
        return R.error(ex.getMessage());
    }

}

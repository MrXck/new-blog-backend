package com.blog.aspect;

import com.blog.exception.APIException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Aspect
@Component
public class TransactionAspect {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Around("execution(* com.blog.controller..insert*(..)) || " +
            "execution(* com.blog.controller..update*(..)) || " +
            "execution(* com.blog.controller..delete*(..)) || " +
            "execution(* com.blog.controller..register*(..))")
    public Object recordOperationLog(ProceedingJoinPoint joinPoint) {
        return transactionTemplate.execute(status -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                // 回滚事务
                status.setRollbackOnly();
                throw new APIException(e.getMessage());
            }
        });
    }
}

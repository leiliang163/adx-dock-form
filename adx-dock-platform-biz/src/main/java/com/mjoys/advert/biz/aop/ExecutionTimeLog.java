package com.mjoys.advert.biz.aop;

import com.mjoys.advert.biz.utils.TimeProfileUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/25 17:14.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Component
@Aspect
public class ExecutionTimeLog {
    /**
     * The Constant FLAT.
     */
    private static final String FLAT = ".";

    /**
     * The Constant BLANK.
     */
    private static final String BLANK = " ";

    /**
     * Remote service aspect.
     */
    @Pointcut("(execution(public * com.mjoys.advert.biz.remoteservice.*.*(..))) || (execution(public * com.mjoys.advert.deploy.controller.*.*(..)))")
    public void remoteServiceAspect() {
        //
    }

    /**
     * Service aspect.
     */
    @Pointcut("execution(public * com.mjoys.advert.biz.*.impl.*.*(..))")
    public void serviceAspect() {
        //
    }

    /**
     * Remote service around.
     *
     * @param joinPoint the join point
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("remoteServiceAspect()")
    public Object remoteServiceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        TimeProfileUtils.start();
        Object obj= around(joinPoint);
        TimeProfileUtils.end();
        return obj;
    }

    /**
     * Service around.
     *
     * @param joinPoint the join point
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("serviceAspect()")
    public Object serviceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return around(joinPoint);
    }

    /**
     * Around.
     *
     * @param joinPoint the join point
     * @return the object
     * @throws Throwable the throwable
     */
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        String fullName = signature.getDeclaringTypeName();
        TimeProfileUtils.enter(fullName.substring(fullName.lastIndexOf(FLAT) + 1) + BLANK + signature.getName());
        Object ret = joinPoint.proceed();
        TimeProfileUtils.release();
        return ret;
    }
}

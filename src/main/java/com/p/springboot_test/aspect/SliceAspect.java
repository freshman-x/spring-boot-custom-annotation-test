package com.p.springboot_test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Spring AOP允许你在方法调用前后执行逻辑。你需要一个「切面」来自动处理@Slice注解：
 */
@Aspect
@Component
public class SliceAspect {
    @Around("execution(* *(.., @Slice (*), ..))")
    public Object handleSliceAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        SliceProcessor.processSlices(joinPoint.getTarget(), method.getName(), args);
        return joinPoint.proceed(args);
    }
}

package com.p.springboot_test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Spring AOP允许你在方法调用前后执行逻辑。你需要一个「切面」来自动处理@Slice注解：
 */
@Aspect
@Component
public class SliceAspect {
    @Around("execution(* *(.., @Slice (*), ..))")
    public Object handleSlicedAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Method method = getMethod(joinPoint);

        if (method == null)
            return joinPoint.proceed();

        Annotation[][] parameterAnnotations = method.getParameterAnnotations(); // 获取方法参数的注解

        // 遍历方法参数，查找 @Slice 注解
        for (int i = 0; i < args.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if (annotation instanceof Slice && args[i] instanceof List) {
                    int chunkSize = ((Slice) annotation).size();
                    List<?> list = (List<?>) args[i];

                    // 分段处理列表
                    for (int j = 0; j < list.size(); j += chunkSize) {
                        List<?> chunk = list.subList(j, Math.min(j + chunkSize, list.size()));
                        args[i] = chunk;  // 替换参数为当前分段
                        joinPoint.proceed(args); // 调用方法，传入当前分段
                    }
                    return null; // 返回空，因为我们已经在分段中执行了目标方法
                }
            }
        }
        return joinPoint.proceed(); // 这块逻辑只有在没找到注解时才会执行，找到了注解就会在 42 行 return
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Method method = joinPoint.getSignature().getDeclaringType()
                .getMethod(joinPoint.getSignature().getName(), ((MethodSignature) joinPoint.getSignature()).getParameterTypes());
        return method;
    }
}

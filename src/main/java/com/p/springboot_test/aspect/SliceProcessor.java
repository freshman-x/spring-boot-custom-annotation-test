package com.p.springboot_test.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * 创建一个工具类，通过反射处理带有@Slice注解的方法参数：
 */
public class SliceProcessor {

    public static void processSlices(Object target, String methodName, Object... args) throws Exception {
        Method method = findMethod(target.getClass(), methodName, args);

        if (method != null) {
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i].isAnnotationPresent(Slice.class)) {
                    Slice slice = parameters[i].getAnnotation(Slice.class);
                    if (args[i] instanceof List) {
                        List<?> list = (List<?>) args[i];
                        int start = Math.max(0, slice.start());
                        int end = Math.min(list.size(), slice.end());
                        args[i] = list.subList(start, end);
                    }
                }
            }
        }
    }

    private static Method findMethod(Class<?> clazz, String methodName, Object[] args) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals(methodName) && method.getParameterCount() == args.length) {
                return method;
            }
        }
        return null;
    }
}

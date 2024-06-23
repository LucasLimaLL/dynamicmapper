package com.lucaslima.dynamicmapper.mapper;

import java.lang.reflect.Method;

public class DynamicMapper {

    public static <T> T invokeMethodWithCast(String className, String methodName, Class<?>[] parameterTypes, Object[] parameters, Class<T> returnTypeClass) throws Exception {

        Class<?> clazz = Class.forName(className);
        Method method = clazz.getMethod(methodName, parameterTypes);
        Object instance = clazz.getDeclaredConstructor().newInstance();
        Object result = method.invoke(instance, parameters);
        return returnTypeClass.cast(result);
    }
}

package com.lucaslima.dynamicmapper.infra.dynamic;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import com.lucaslima.dynamicmapper.core.domain.MapperType;
import com.lucaslima.dynamicmapper.core.ports.out.MapperPort;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
public class DynamicMapperAdapter implements MapperPort {

    @Override
    @SneakyThrows
    public Object map(MapperProperty mapperProperty, Field selectedField, Object object) throws IllegalAccessException {

        var parameterTypes = new Class[]{selectedField.get(object).getClass()};
        var parameters = new Object[]{selectedField.get(object)};
        var returnTypeClass = (Class<?>) mapperProperty.value()[0];
        Class<?> clazz = Class.forName(mapperProperty.value()[1].toString());
        Method method = clazz.getMethod(mapperProperty.value()[2].toString(), parameterTypes);
        Object instance = clazz.getDeclaredConstructor().newInstance();
        Object result = method.invoke(instance, parameters);
        return returnTypeClass.cast(result);

    }

    @Override
    public boolean apply(MapperProperty mapperProperty) {
        return MapperType.DYNAMIC.equals(mapperProperty.mapperType());
    }
}

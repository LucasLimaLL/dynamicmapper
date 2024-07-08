package com.lucaslima.dynamicmapper.infra.dynamic;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import com.lucaslima.dynamicmapper.core.domain.MapperType;
import com.lucaslima.dynamicmapper.core.ports.out.MapperValuePort;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class DynamicMapperValueAdapter implements MapperValuePort {

    @Override
    public Object map(MapperProperty mapperProperty, Object object, Class<?> aClass) throws Exception {

        var parameterTypes = new Class[]{aClass};
        var parameters = new Object[]{object};
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

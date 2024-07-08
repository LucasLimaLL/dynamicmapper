package com.lucaslima.dynamicmapper.infra.simple;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import com.lucaslima.dynamicmapper.core.domain.MapperType;
import com.lucaslima.dynamicmapper.core.ports.out.MapperValuePort;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class SimpleMapperValueAdapter implements MapperValuePort {

    @Override
    public Object map(MapperProperty mapperProperty, Object object, Class<?> aClass) throws IllegalAccessException {
        return object;
    }

    @Override
    public boolean apply(MapperProperty mapperProperty) {
        return MapperType.SIMPLE.equals(mapperProperty.mapperType());
    }
}

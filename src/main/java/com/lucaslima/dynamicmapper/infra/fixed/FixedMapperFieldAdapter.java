package com.lucaslima.dynamicmapper.infra.fixed;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import com.lucaslima.dynamicmapper.core.domain.MapperType;
import com.lucaslima.dynamicmapper.core.ports.out.MapperFieldPort;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class FixedMapperFieldAdapter implements MapperFieldPort {
    @Override
    public Object map(MapperProperty mapperProperty, Field selectedField, Object object) throws IllegalAccessException {
        var returnTypeClass = (Class<?>) mapperProperty.value()[0];
        return returnTypeClass.cast(mapperProperty.value()[1]);
    }

    @Override
    public boolean apply(MapperProperty mapperProperty) {
        return MapperType.FIXED.equals(mapperProperty.mapperType());
    }
}

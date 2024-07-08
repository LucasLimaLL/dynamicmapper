package com.lucaslima.dynamicmapper.infra.simple;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import com.lucaslima.dynamicmapper.core.domain.MapperType;
import com.lucaslima.dynamicmapper.core.ports.out.MapperFieldPort;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class SimpleMapperFieldAdapter implements MapperFieldPort {

    @Override
    public Object map(MapperProperty mapperProperty, Field selectedField, Object object) throws Exception {
        selectedField.setAccessible(true);
        return selectedField.get(object);
    }

    @Override
    public boolean apply(MapperProperty mapperProperty) {
        return MapperType.SIMPLE.equals(mapperProperty.mapperType());
    }
}

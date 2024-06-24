package com.lucaslima.dynamicmapper.infra.simple;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import com.lucaslima.dynamicmapper.core.domain.MapperType;
import com.lucaslima.dynamicmapper.core.ports.out.MapperPort;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class SimpleMapperAdapter implements MapperPort {

    @Override
    public Object map(MapperProperty mapperProperty, Field selectedField, Object object) throws IllegalAccessException {
        Field[] fieldsSource = object.getClass().getDeclaredFields();

        selectedField.setAccessible(true);
        return selectedField.get(object);
    }

    @Override
    public boolean apply(MapperProperty mapperProperty) {
        return MapperType.SIMPLE.equals(mapperProperty.mapperType());
    }
}

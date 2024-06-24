package com.lucaslima.dynamicmapper.core.ports.out;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;

import java.util.List;

public interface GetMapperPropertiesPort {

    List<MapperProperty> getMapperProperties(Object object, Class<?> returnTypeClass);
}

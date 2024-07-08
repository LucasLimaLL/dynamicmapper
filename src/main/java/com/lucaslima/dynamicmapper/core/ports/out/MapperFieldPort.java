package com.lucaslima.dynamicmapper.core.ports.out;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;

import java.lang.reflect.Field;

public interface MapperFieldPort {

    Object map(MapperProperty mapperProperty, Field selectedField, Object object) throws IllegalAccessException, Exception;

    boolean apply(MapperProperty mapperProperty);
}

package com.lucaslima.dynamicmapper.core.ports.in;

public interface MapperUseCase {

    Object map(Object jsonObject, Class<?> returnTypeClass) throws IllegalAccessException;
}

package com.lucaslima.dynamicmapper.core.ports.in;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MapperUseCase {

    Object map(Object jsonObject, Class<?> returnTypeClass) throws Exception;
}

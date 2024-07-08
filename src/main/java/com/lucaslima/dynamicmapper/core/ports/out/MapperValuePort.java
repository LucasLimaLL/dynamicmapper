package com.lucaslima.dynamicmapper.core.ports.out;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import org.json.JSONObject;

public interface MapperValuePort {

    Object map(MapperProperty mapperProperty, Object object, Class<?> aClass) throws Exception;

    boolean apply(MapperProperty mapperProperty);
}

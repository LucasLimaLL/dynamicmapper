package com.lucaslima.dynamicmapper.core.ports.out;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import lombok.SneakyThrows;

import java.util.List;

public interface GetMapperPropertiesPort {

    @SneakyThrows
    List<MapperProperty> getMapperProperties(String key);
}

package com.lucaslima.dynamicmapper.infra.properties;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import com.lucaslima.dynamicmapper.core.domain.MapperType;
import com.lucaslima.dynamicmapper.core.ports.out.GetMapperPropertiesPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetMapperPropertiesAdapter implements GetMapperPropertiesPort {

    @Override
    public List<MapperProperty> getMapperProperties(Object object, Class<?> returnTypeClass) {
        return List.of(
                new MapperProperty("name", "name", MapperType.DYNAMIC, String.class, "com.lucaslima.dynamicmapper.infra.dynamic.PersonDynamicMapper", "getFirstName"),
                new MapperProperty("lastName", "name", MapperType.DYNAMIC, String.class, "com.lucaslima.dynamicmapper.infra.dynamic.PersonDynamicMapper", "getLastName"),
                new MapperProperty("age", "birthDate", MapperType.DYNAMIC, Integer.class, "com.lucaslima.dynamicmapper.infra.dynamic.PersonDynamicMapper", "getAge"),
                new MapperProperty("email", "email", MapperType.SIMPLE, "email"),
                new MapperProperty("phone", "phone", MapperType.SIMPLE, "phone"),
                new MapperProperty("genderType", "gender", MapperType.NORMALIZER, "gender")
        );
    }
}

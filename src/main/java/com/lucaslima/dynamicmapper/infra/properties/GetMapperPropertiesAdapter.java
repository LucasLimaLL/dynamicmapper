package com.lucaslima.dynamicmapper.infra.properties;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import com.lucaslima.dynamicmapper.core.domain.MapperType;
import com.lucaslima.dynamicmapper.core.ports.out.GetMapperPropertiesPort;
import com.lucaslima.dynamicmapper.dto.Client;
import com.lucaslima.dynamicmapper.dto.Enterprise;
import com.lucaslima.dynamicmapper.dto.Person;
import com.lucaslima.dynamicmapper.dto.Worker;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GetMapperPropertiesAdapter implements GetMapperPropertiesPort {

    Map<String, List<MapperProperty>> PROPERTIES = Map.of(
            Person.class.getName() + Client.class.getName(),
            List.of(
                    new MapperProperty("name", "name", MapperType.valueOf("DYNAMIC"), Class.forName("java.lang.String"), "com.lucaslima.dynamicmapper.infra.dynamic.PersonDynamicMapper", "getFirstName"),
                    new MapperProperty("lastName", "name", MapperType.valueOf("DYNAMIC"), Class.forName("java.lang.String"), "com.lucaslima.dynamicmapper.infra.dynamic.PersonDynamicMapper", "getLastName"),
                    new MapperProperty("age", "birthDate", MapperType.valueOf("DYNAMIC"), Class.forName("java.lang.Integer"), "com.lucaslima.dynamicmapper.infra.dynamic.PersonDynamicMapper", "getAge"),
                    new MapperProperty("email", "email", MapperType.valueOf("DYNAMIC"), Class.forName("java.lang.String"), "com.lucaslima.dynamicmapper.infra.dynamic.PersonDynamicMapper", "getEmail"),
                    new MapperProperty("phone", "phone", MapperType.valueOf("SIMPLE"), "phone"),
                    new MapperProperty("genderType", "gender", MapperType.valueOf("NORMALIZER"), "gender")
            ),Person.class.getName() + Worker.class.getName(),
            List.of(
                    new MapperProperty("name", "name", MapperType.valueOf("DYNAMIC"), Class.forName("java.lang.String"), "com.lucaslima.dynamicmapper.infra.dynamic.PersonDynamicMapper", "getFirstName"),
                    new MapperProperty("lastName", "name", MapperType.valueOf("DYNAMIC"), Class.forName("java.lang.String"), "com.lucaslima.dynamicmapper.infra.dynamic.PersonDynamicMapper", "getLastName"),
                    new MapperProperty("age", "birthDate", MapperType.valueOf("DYNAMIC"), Class.forName("java.lang.Integer"), "com.lucaslima.dynamicmapper.infra.dynamic.PersonDynamicMapper", "getAge"),
                    new MapperProperty("email", "email", MapperType.valueOf("DYNAMIC"), Class.forName("java.lang.String"), "com.lucaslima.dynamicmapper.infra.dynamic.PersonDynamicMapper", "getEmail"),
                    new MapperProperty("phone", "phone", MapperType.valueOf("SIMPLE"), "phone"),
                    new MapperProperty("genderType", "gender", MapperType.valueOf("NORMALIZER"), "gender")
            ),
            Person.class.getName() + Enterprise.class.getName(),
            List.of(
                    new MapperProperty("name", "name", MapperType.valueOf("SIMPLE"), ""),
                    new MapperProperty("documentNumber", "documentNumber", MapperType.valueOf("SIMPLE"), ""),
                    new MapperProperty("type", null, MapperType.valueOf("FIXED"), Class.forName("java.lang.String"), "JURIDICA"),
                    new MapperProperty("phone", "phone", MapperType.valueOf("SIMPLE"), "")

            )
    );

    public GetMapperPropertiesAdapter() throws ClassNotFoundException {
    }

    @SneakyThrows
    @Override
    public List<MapperProperty> getMapperProperties(Object object, Class<?> returnTypeClass) {
        return PROPERTIES.get(object.getClass().getName() + returnTypeClass.getName());
    }
}

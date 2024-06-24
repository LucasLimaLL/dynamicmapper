package com.lucaslima.dynamicmapper.core.services;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import com.lucaslima.dynamicmapper.core.domain.SpecificField;
import com.lucaslima.dynamicmapper.core.ports.in.MapperUseCase;
import com.lucaslima.dynamicmapper.core.ports.out.GetMapperPropertiesPort;
import com.lucaslima.dynamicmapper.core.ports.out.MapperPort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExecuteMapperService implements MapperUseCase {

    private final List<MapperPort> mapperPorts;
    private final GetMapperPropertiesPort getMapperPropertiesPort;

    public ExecuteMapperService(List<MapperPort> mapperPorts,
                                GetMapperPropertiesPort getMapperPropertiesPort) {
        this.mapperPorts = mapperPorts;
        this.getMapperPropertiesPort = getMapperPropertiesPort;
    }

    @Override
    public Object map(Object object, Class<?> returnTypeClass) throws IllegalAccessException {

        List<MapperProperty> mapperProperties = getMapperPropertiesPort.getMapperProperties(object, returnTypeClass);
        List<Field> fieldsSource = Arrays.asList(object.getClass().getDeclaredFields());
        Field[] fieldsDestination = returnTypeClass.getDeclaredFields();
        List<SpecificField> specificFields = new ArrayList<>();

        for (Field field : fieldsDestination) {
            field.setAccessible(true);

            var property = mapperProperties.stream()
                    .filter(mapProperty -> field.getName().equalsIgnoreCase(mapProperty.destinationField()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Destination field mapping " + field.getName() + " not found"));

            var selectedField = fieldsSource
                    .stream()
                    .filter(f -> f.getName().equalsIgnoreCase(property.originField()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Origin field " + property.originField() + " not found"));
            selectedField.setAccessible(true);

            var fieldMapped = mapperPorts
                    .stream()
                    .filter(mapperPort -> mapperPort.apply(property))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Not found any mapper for field " + field.getName()))
                    .map(property, selectedField, object);

            specificFields.add(new SpecificField(field.getName(), fieldMapped));
        }
        return createObjectDynamically(returnTypeClass, specificFields);
    }

    private Object createObjectDynamically(Class<?> returnTypeClass, List<SpecificField> specificFields) {
        return null;
    }


}

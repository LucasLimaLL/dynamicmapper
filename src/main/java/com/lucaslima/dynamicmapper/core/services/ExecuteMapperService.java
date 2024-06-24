package com.lucaslima.dynamicmapper.core.services;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import com.lucaslima.dynamicmapper.core.domain.SpecificField;
import com.lucaslima.dynamicmapper.core.ports.in.MapperUseCase;
import com.lucaslima.dynamicmapper.core.ports.out.ConstructorPort;
import com.lucaslima.dynamicmapper.core.ports.out.GetMapperPropertiesPort;
import com.lucaslima.dynamicmapper.core.ports.out.MapperPort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExecuteMapperService implements MapperUseCase {

    private final List<MapperPort> mapperPorts;
    private final GetMapperPropertiesPort getMapperPropertiesPort;
    private final ConstructorPort constructorPort;

    public ExecuteMapperService(List<MapperPort> mapperPorts,
                                GetMapperPropertiesPort getMapperPropertiesPort,
                                ConstructorPort constructorPort) {
        this.mapperPorts = mapperPorts;
        this.getMapperPropertiesPort = getMapperPropertiesPort;
        this.constructorPort = constructorPort;
    }

    @Override
    public Object map(Object object, Class<?> returnTypeClass) throws IllegalAccessException {

        List<MapperProperty> mapperProperties = getMapperPropertiesPort.getMapperProperties(object, returnTypeClass);
        List<Field> fieldsSource = Arrays.asList(object.getClass().getDeclaredFields());
        Field[] fieldsDestination = returnTypeClass.getDeclaredFields();
        List<SpecificField> specificFields = new ArrayList<>();

        for (Field field : fieldsDestination) {
            field.setAccessible(true);

            var property = getMapperProperty(field, mapperProperties);
            var selectedField = getSelectedField(fieldsSource, property);
            var fieldMapped = getFieldMapped(object, field, property, selectedField);

            specificFields.add(new SpecificField(field.getName(), fieldMapped));
        }

        Constructor<?>[] constructors = returnTypeClass.getDeclaredConstructors();
        Constructor<?> largestConstructor = null;
        int maxParams = 0;
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() > maxParams) {
                maxParams = constructor.getParameterCount();
                largestConstructor = constructor;
            }
        }

        return constructorPort.construct(largestConstructor, returnTypeClass, specificFields);
    }

    private Object getFieldMapped(Object object, Field field, MapperProperty property, Field selectedField) throws IllegalAccessException {
        return mapperPorts
                .stream()
                .filter(mapperPort -> mapperPort.apply(property))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found any mapper for field " + field.getName()))
                .map(property, selectedField, object);
    }

    private static Field getSelectedField(List<Field> fieldsSource, MapperProperty property) {
        var selectedField = fieldsSource
                .stream()
                .filter(f -> f.getName().equalsIgnoreCase(property.originField()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Origin field " + property.originField() + " not found"));
        selectedField.setAccessible(true);
        return selectedField;
    }

    private static MapperProperty getMapperProperty(Field field, List<MapperProperty> mapperProperties) {
        return mapperProperties.stream()
                .filter(mapProperty -> field.getName().equalsIgnoreCase(mapProperty.destinationField()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Destination field mapping " + field.getName() + " not found"));
    }

}

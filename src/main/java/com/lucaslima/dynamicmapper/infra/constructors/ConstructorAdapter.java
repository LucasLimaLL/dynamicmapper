package com.lucaslima.dynamicmapper.infra.constructors;

import com.lucaslima.dynamicmapper.core.domain.SpecificField;
import com.lucaslima.dynamicmapper.core.ports.out.ConstructorPort;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.List;

@Component
public class ConstructorAdapter implements ConstructorPort {

    @Override
    @SneakyThrows
    public Object construct(Constructor<?> largestConstructor, Class<?> returnTypeClass, List<SpecificField> specificFields) {
        return largestConstructor == null
                ? constructWithSetters(returnTypeClass, specificFields)
                : constructWithConstructor(largestConstructor, specificFields);
    }

    private Object constructWithConstructor(Constructor<?> largestConstructor, List<SpecificField> specificFields) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Object[] constructorArgs = new Object[largestConstructor.getParameterCount()];
        Parameter[] parameters = largestConstructor.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            String paramName = parameters[i].getName();

            var field = specificFields
                    .stream()
                    .filter(specificField -> paramName.equalsIgnoreCase(specificField.field()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Value not provided for parameter " + paramName));

            constructorArgs[i] = field.value();
        }

        return largestConstructor.newInstance(constructorArgs);
    }

    private Object constructWithSetters(Class<?> returnTypeClass, List<SpecificField> specificFields) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object instance = returnTypeClass.getDeclaredConstructor().newInstance();
        Field[] fields = returnTypeClass.getDeclaredFields();

        for (Field field : fields) {
            var specificField = specificFields
                    .stream()
                    .filter(specific -> field.getName().equalsIgnoreCase(specific.field()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Value not provided for field " + field.getName()));
            field.setAccessible(true);
            field.set(instance, specificField.value());
        }

        return instance;
    }


}

package com.lucaslima.dynamicmapper.core.ports.out;

import com.lucaslima.dynamicmapper.core.domain.SpecificField;

import java.lang.reflect.Constructor;
import java.util.List;

public interface ConstructorPort {

    Object construct(Constructor<?> largestConstructor, Class<?> returnTypeClass, List<SpecificField> specificFields);

}

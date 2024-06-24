package com.lucaslima.dynamicmapper.infra.normalizer;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import com.lucaslima.dynamicmapper.core.domain.MapperType;
import com.lucaslima.dynamicmapper.core.ports.out.MapperPort;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Component
public class NormalizerMapperAdapter implements MapperPort {

    private static final Map<String, List<Map<Object, Object>>> CONTEXTS =
            Map.of("gender", List.of(
                    Map.of("M", "MASCULINO"),
                    Map.of("F", "FEMININO")
            ));

    @Override
    public Object map(MapperProperty mapperProperty, Field selectedField, Object value) throws IllegalAccessException {

        String context = mapperProperty.value()[0].toString();
        var contextMap = CONTEXTS.get(context);
        if (contextMap.isEmpty()) {
            throw new RuntimeException("Context not found");
        }

        for (Map<Object, Object> map : contextMap) {
            if (map.containsKey(selectedField.get(value))) {
                return map.get(selectedField.get(value));
            }
        }

        throw new RuntimeException("Origin " + value + " not found for context " + context);
    }

    @Override
    public boolean apply(MapperProperty mapperProperty) {
        return MapperType.NORMALIZER.equals(mapperProperty.mapperType());
    }
}

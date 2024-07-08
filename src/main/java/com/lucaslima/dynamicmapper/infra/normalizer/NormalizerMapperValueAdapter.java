package com.lucaslima.dynamicmapper.infra.normalizer;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import com.lucaslima.dynamicmapper.core.domain.MapperType;
import com.lucaslima.dynamicmapper.core.ports.out.MapperValuePort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class NormalizerMapperValueAdapter implements MapperValuePort {

    private static final Map<String, List<Map<Object, Object>>> CONTEXTS =
            Map.of("gender", List.of(
                    Map.of("M", "MASCULINO"),
                    Map.of("F", "FEMININO")
            ));

    @Override
    public Object map(MapperProperty mapperProperty, Object value, Class<?> aClass) throws Exception {


        String context = mapperProperty.value()[0].toString();
        var contextMap = CONTEXTS.get(context);
        if (contextMap.isEmpty()) {
            throw new RuntimeException("Context not found");
        }



        for (Map<Object, Object> map : contextMap) {
            if (map.containsKey(value)) {
                return map.get(value);
            }
        }

        throw new RuntimeException("Origin " + value + " not found for context " + context);
    }

    @Override
    public boolean apply(MapperProperty mapperProperty) {
        return MapperType.NORMALIZER.equals(mapperProperty.mapperType());
    }
}

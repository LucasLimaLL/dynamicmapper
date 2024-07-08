package com.lucaslima.dynamicmapper.infra.fixed;

import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import com.lucaslima.dynamicmapper.core.domain.MapperType;
import com.lucaslima.dynamicmapper.core.ports.out.MapperValuePort;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class FixedMapperValueAdapter implements MapperValuePort {
    @Override
    public Object map(MapperProperty mapperProperty, Object object, Class<?> aClass)  {
        var returnTypeClass = (Class<?>) mapperProperty.value()[0];
        return returnTypeClass.cast(mapperProperty.value()[1]);
    }

    @Override
    public boolean apply(MapperProperty mapperProperty) {
        return MapperType.FIXED.equals(mapperProperty.mapperType());
    }
}

package com.lucaslima.dynamicmapper.core.ports.out;

import com.lucaslima.dynamicmapper.core.domain.DynamicSchema;

public interface GetSchemaPort {

    DynamicSchema getSchema(String name);
}

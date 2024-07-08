package com.lucaslima.dynamicmapper.infra.schema;

import com.lucaslima.dynamicmapper.core.domain.DynamicSchema;
import com.lucaslima.dynamicmapper.core.ports.out.GetSchemaPort;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GetSchemaAdapter implements GetSchemaPort {

    Map<String, String> SCHEMAS = Map.of(
            "com.lucaslima.dynamicmapper.dto.Client", "{\"$schema\":\"http://json-schema.org/draft-07/schema#\",\"title\":\"Client\",\"type\":\"object\",\"properties\":{\"name\":{\"type\":\"string\"},\"lastName\":{\"type\":\"string\"},\"age\":{\"type\":\"integer\"},\"email\":{\"type\":\"string\",\"format\":\"email\"},\"phone\":{\"type\":\"string\"},\"genderType\":{\"type\":\"string\"}},\"required\":[\"name\",\"lastName\",\"age\",\"email\",\"phone\",\"genderType\"]}",
            "com.lucaslima.dynamicmapper.dto.Person", "{\"$schema\":\"http://json-schema.org/draft-07/schema#\",\"title\":\"Person\",\"type\":\"object\",\"properties\":{\"name\":{\"type\":\"string\"},\"birthDate\":{\"type\":\"string\",\"format\":\"date\"},\"documentNumber\":{\"type\":\"string\"},\"email\":{\"type\":\"object\",\"properties\":{\"value\":{\"type\":\"string\",\"format\":\"email\"}},\"required\":[\"value\"]},\"phone\":{\"type\":\"string\"},\"gender\":{\"type\":\"string\"},\"address\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"properties\":{\"address\":{\"type\":\"string\"},\"number\":{\"type\":\"string\"},\"complement\":{\"type\":\"string\"},\"neighborhood\":{\"type\":\"string\"},\"city\":{\"type\":\"string\"},\"state\":{\"type\":\"string\"},\"zipCode\":{\"type\":\"string\"}},\"required\":[\"address\",\"number\",\"neighborhood\",\"city\",\"state\",\"zipCode\"]}}},\"required\":[\"name\",\"birthDate\",\"documentNumber\",\"email\",\"phone\",\"gender\",\"address\"]}"
    );

    @Override
    public DynamicSchema getSchema(String name) {
        String content = SCHEMAS.get(name);

        return new DynamicSchema(null, name, "1", content);
    }
}

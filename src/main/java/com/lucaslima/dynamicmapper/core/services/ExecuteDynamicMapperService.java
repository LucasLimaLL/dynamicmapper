package com.lucaslima.dynamicmapper.core.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lucaslima.dynamicmapper.core.domain.DynamicSchema;
import com.lucaslima.dynamicmapper.core.domain.MapperProperty;
import com.lucaslima.dynamicmapper.core.ports.in.MapperUseCase;
import com.lucaslima.dynamicmapper.core.ports.out.GetMapperPropertiesPort;
import com.lucaslima.dynamicmapper.core.ports.out.GetSchemaPort;
import com.lucaslima.dynamicmapper.core.ports.out.MapperFieldPort;
import com.lucaslima.dynamicmapper.core.ports.out.MapperValuePort;
import lombok.SneakyThrows;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecuteDynamicMapperService implements MapperUseCase {

    private final GetSchemaPort getSchemaPort;
    private final List<MapperValuePort> mapperFieldPorts;
    private final GetMapperPropertiesPort getMapperPropertiesPort;
    private final ObjectMapper objectMapper;

    public ExecuteDynamicMapperService(
            List<MapperValuePort> mapperFieldPorts,
            GetSchemaPort getSchemaPort,
            GetMapperPropertiesPort getMapperPropertiesPort) {
        this.mapperFieldPorts = mapperFieldPorts;
        this.getSchemaPort = getSchemaPort;
        this.getMapperPropertiesPort = getMapperPropertiesPort;
        this.objectMapper = JsonMapper.builder()
                .findAndAddModules()
                .build();
    }

    @SneakyThrows
    public Object map(Object object, Class<?> returnTypeClass) throws Exception {
        return object instanceof String ?
                processJson((String) object, "com.lucaslima.dynamicmapper.dto.Person", returnTypeClass.getName())
                : processJson(objectMapper.writeValueAsString(object), object.getClass().getName(), returnTypeClass.getName());
    }


    public String processJson(String inputJsonString, String sourceKey, String destinationKey) throws Exception {

        List<MapperProperty> mapperProperties = getMapperPropertiesPort.getMapperProperties(sourceKey.concat(destinationKey));
        DynamicSchema source = getSchemaPort.getSchema(sourceKey);
        DynamicSchema destination = getSchemaPort.getSchema(destinationKey);
        Schema inputSchema = SchemaLoader.load(new JSONObject(source.content()));
        Schema outputSchema = SchemaLoader.load(new JSONObject(destination.content()));

        JSONObject inputJson = new JSONObject(inputJsonString);
        validateJson(inputSchema, inputJson);

        ObjectNode outputJson = objectMapper.createObjectNode();

        for (MapperProperty property : mapperProperties) {
            outputJson.put(property.destinationField(), "");
            MapperValuePort port = mapperFieldPorts
                    .stream()
                    .filter(mapperFieldPort -> mapperFieldPort.apply(property))
                    .findFirst()
                    .get();

            System.out.println(port.map(property, inputJson, inputJson.getClass()));
        }

        return outputJson.toString();
    }

    public void validateJson(Schema schema, JSONObject json) {
        schema.validate(json);
    }

}

package com.lucaslima.dynamicmapper.core.domain;

public record MapperProperty(
        String destinationField,
        String originField,
        MapperType mapperType,
        Object... value) {
}

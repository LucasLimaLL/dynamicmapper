package com.lucaslima.dynamicmapper.mapper;

import java.time.Instant;

public record Person
        (String name,
         Instant birthDate,
         Email email,
         String phone) {
}

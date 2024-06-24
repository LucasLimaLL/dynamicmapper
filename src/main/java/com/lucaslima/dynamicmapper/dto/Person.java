package com.lucaslima.dynamicmapper.dto;

import java.time.Instant;

public record Person
        (String name,
         Instant birthDate,
         Email email,
         String phone,
         String gender) {
}

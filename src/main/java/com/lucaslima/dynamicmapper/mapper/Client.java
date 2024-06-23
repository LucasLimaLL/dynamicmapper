package com.lucaslima.dynamicmapper.mapper;

public record Client
        (String name,
         String lastName,
         int age,
         String email,
         String phone) {
}

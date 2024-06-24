package com.lucaslima.dynamicmapper.dto;

public record Client
        (String name,
         String lastName,
         int age,
         String email,
         String phone,
         String genderType) {
}

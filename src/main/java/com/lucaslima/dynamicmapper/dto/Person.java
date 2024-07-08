package com.lucaslima.dynamicmapper.dto;

import java.time.LocalDate;
import java.util.List;

public record Person
        (String name,
         LocalDate birthDate,
         String documentNumber,
         Email email,
         String phone,
         String gender,
         List<Address> address) {
}

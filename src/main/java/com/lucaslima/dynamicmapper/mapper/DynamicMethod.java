package com.lucaslima.dynamicmapper.mapper;

import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;

public class DynamicMethod {

    public Client toClient(Person person) {

        String[] fullName = person.name().split(" ");
        String firstName = fullName[0];
        String lastName = fullName[fullName.length - 1];
        int age = Period.between(
                person.birthDate().atZone(ZoneId.systemDefault()).toLocalDate(),
                Instant.now().atZone(ZoneId.systemDefault()).toLocalDate()).getYears();

        return new Client(
                firstName,
                lastName,
                age,
                person.email().email(),
                person.phone());
    }
}

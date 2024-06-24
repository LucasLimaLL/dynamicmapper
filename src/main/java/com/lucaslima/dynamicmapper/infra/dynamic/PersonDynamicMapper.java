package com.lucaslima.dynamicmapper.infra.dynamic;

import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;

public class PersonDynamicMapper {

    public String getFirstName(String name) {
        return name.split(" ")[0];
    }

    public String getLastName(String name) {
        var fullName = name.split(" ");
        return fullName[fullName.length - 1];
    }

    public Integer getAge(Instant birthDate) {
        return Period.between(
                birthDate.atZone(ZoneId.systemDefault()).toLocalDate(),
                Instant.now().atZone(ZoneId.systemDefault()).toLocalDate()).getYears();
    }
}

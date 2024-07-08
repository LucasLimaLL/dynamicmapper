package com.lucaslima.dynamicmapper.infra.dynamic;

import com.lucaslima.dynamicmapper.dto.Email;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class PersonDynamicMapper {

    public String getFirstName(String name) {
        return name.split(" ")[0];
    }

    public String getFirstName(JSONObject jsonObject) {
        return jsonObject.getString("name").split(" ")[0];
    }

    public String getLastName(String name) {
        var fullName = name.split(" ");
        return fullName[fullName.length - 1];
    }

    public String getLastName(JSONObject jsonObject) {
        var fullName = jsonObject.getString("name").split(" ");
        return fullName[fullName.length - 1];
    }

    public Integer getAge(LocalDate birthDate) {
        return Period.between(
                birthDate,
                Instant.now().atZone(ZoneId.systemDefault()).toLocalDate()).getYears();
    }

    public Integer getAge(JSONObject birthDate) {
        return getAge(LocalDate.parse(birthDate.getString("birthDate")));
    }

    public String getEmail(Email email) {
        return email.email();
    }

    public String getEmail(JSONObject email) {
        return email.getJSONObject("email").getString("value");
    }
}

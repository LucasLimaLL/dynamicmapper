package com.lucaslima.dynamicmapper;

import com.lucaslima.dynamicmapper.core.ports.in.MapperUseCase;
import com.lucaslima.dynamicmapper.dto.Client;
import com.lucaslima.dynamicmapper.dto.Email;
import com.lucaslima.dynamicmapper.dto.Person;
import com.lucaslima.dynamicmapper.service.DynamicFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.time.ZoneOffset;

@SpringBootApplication
public class DynamicmapperApplication {

    @Autowired
    MapperUseCase mapperUseCase;

    public static void main(String[] args) {
        SpringApplication.run(DynamicmapperApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws Exception {
        Object object = new Person(
                "Nome do Fulano",
                LocalDate.parse("1990-01-01").atStartOfDay().toInstant(ZoneOffset.UTC),
                new Email("teste@teste.com"),
                "123456789",
                "M"
        );

        System.out.println(mapperUseCase.map(object, Client.class));

    }

    public Object getObject(Object object, String method, Class<?> returnClass) throws Exception {
        return DynamicFunctionService.invokeMethodWithCast(
                "com.lucaslima.dynamicmapper.service.functions.DynamicMethod",
                method,
                new Class[]{object.getClass()},
                new Object[]{object},
                returnClass
        );
    }
}

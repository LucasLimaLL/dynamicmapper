package com.lucaslima.dynamicmapper;

import com.lucaslima.dynamicmapper.mapper.Client;
import com.lucaslima.dynamicmapper.mapper.DynamicMapper;
import com.lucaslima.dynamicmapper.mapper.Email;
import com.lucaslima.dynamicmapper.mapper.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.time.ZoneOffset;

@SpringBootApplication
public class DynamicmapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicmapperApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws Exception {
        Object object = new Person(
                "Nome do Fulano",
                LocalDate.parse("1990-01-01").atStartOfDay().toInstant(ZoneOffset.UTC),
                new Email("teste@teste.com"),
                "123456789"
        );

        System.out.println(getObject(object, "toClient", Client.class));
        
    }

    public Object getObject(Object object, String method, Class<?> returnClass) throws Exception {
        return DynamicMapper.invokeMethodWithCast(
                "com.lucaslima.dynamicmapper.mapper.DynamicMethod",
                method,
                new Class[]{object.getClass()},
                new Object[]{object},
                returnClass
        );
    }
}

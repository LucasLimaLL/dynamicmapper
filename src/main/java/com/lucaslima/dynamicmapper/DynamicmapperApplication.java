package com.lucaslima.dynamicmapper;

import com.lucaslima.dynamicmapper.core.services.ExecuteDynamicMapperService;
import com.lucaslima.dynamicmapper.core.services.ExecuteMapperService;
import com.lucaslima.dynamicmapper.dto.Client;
import com.lucaslima.dynamicmapper.dto.Email;
import com.lucaslima.dynamicmapper.dto.Enterprise;
import com.lucaslima.dynamicmapper.dto.Person;
import com.lucaslima.dynamicmapper.dto.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class DynamicmapperApplication {

    @Autowired
    ExecuteDynamicMapperService executeDynamicMapperService;

    @Autowired
    ExecuteMapperService executeMapperService;

    public static void main(String[] args) {
        SpringApplication.run(DynamicmapperApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws Exception {
        Person object = new Person(
                "Nome do Fulano",
                LocalDate.parse("1990-01-01"),
                "01234567890",
                new Email("teste@teste.com"),
                "123456789",
                "M",
                List.of()
        );

        System.out.println(executeMapperService.map(object, Client.class));
        System.out.println(executeMapperService.map(object, Worker.class));
        System.out.println(executeMapperService.map(object, Enterprise.class));

        System.out.println(executeDynamicMapperService.map("{\"name\":\"Nome do Fulano\",\"birthDate\":\"1990-01-01\",\"documentNumber\":\"01234567890\",\"email\":{\"value\":\"fulano@teste.com\"},\"phone\":\"+5511987654321\",\"gender\":\"M\",\"address\":[{\"address\":\"Rua das Fiandeiras\",\"number\":\"SN\",\"neighborhood\":\"Vila Olímpia\",\"city\":\"São Paulo\",\"state\":\"SP\",\"zipCode\":\"04545005\",\"complement\":\"\"}]}", Client.class));



    }
}

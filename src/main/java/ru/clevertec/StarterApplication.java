package ru.clevertec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.clevertec.service.ExampleService;

@SpringBootApplication
public class StarterApplication {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(StarterApplication.class, args);
        ExampleService service = context.getBean(ExampleService.class);
        service.execute();
    }
}

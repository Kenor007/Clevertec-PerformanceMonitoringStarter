package ru.clevertec.service;

import org.springframework.stereotype.Service;
import ru.clevertec.annotation.MonitorPerformance;

@Service
public class ExampleService {
    @MonitorPerformance
    public void execute() throws InterruptedException {
        System.out.print("Start method");
        Thread.sleep(1);
    }
}

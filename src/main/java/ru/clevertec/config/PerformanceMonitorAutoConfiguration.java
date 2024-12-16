package ru.clevertec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.clevertec.aspect.PerformanceMonitorAspect;
import ru.clevertec.properties.PerformanceMonitorProperties;

@Configuration
@Import({PerformanceMonitorAspect.class, PerformanceMonitorProperties.class})
public class PerformanceMonitorAutoConfiguration {
}

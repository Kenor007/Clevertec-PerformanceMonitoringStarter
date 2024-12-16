package ru.clevertec.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.clevertec.properties.PerformanceMonitorProperties;

@Component
@Aspect
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "performance.monitor", name = "enabled", havingValue = "true", matchIfMissing = true)
public class PerformanceMonitorAspect {
    private static final Logger logger = LoggerFactory.getLogger(PerformanceMonitorAspect.class);
    private final PerformanceMonitorProperties properties;

    @Around("@annotation(ru.clevertec.annotation.MonitorPerformance)")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!properties.isEnabled()) {
            return joinPoint.proceed();
        }

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - startTime;

        if (executionTime > properties.getMinExecutionTime()) {
            String methodName = joinPoint.getSignature().getName();
            System.out.printf("Method [%s] executed in [%d] ms.%n", methodName, executionTime);
            logger.info("Method {} executed in {} ms.%n", methodName, executionTime);
        }
        return result;
    }
}

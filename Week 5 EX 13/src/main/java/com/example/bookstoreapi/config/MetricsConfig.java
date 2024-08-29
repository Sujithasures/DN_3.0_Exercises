package com.example.bookstoreapi.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public void customMetrics(MeterRegistry meterRegistry) {
        meterRegistry.gauge("custom.metric.total_books", 100); // Example custom metric
    }
}

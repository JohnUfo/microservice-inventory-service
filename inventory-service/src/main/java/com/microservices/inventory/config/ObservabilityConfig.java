package com.microservices.inventory.config;


import io.micrometer.observation.ObservationRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
@RequiredArgsConstructor
public class ObservabilityConfig {

    private final ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory;

    @PostConstruct
    public void setObservationForKafkaTemplate() {
        if (concurrentKafkaListenerContainerFactory != null) {
            concurrentKafkaListenerContainerFactory
                    .getContainerProperties()
                    .setObservationEnabled(true);
        }
    }

    @Bean
    public ObservationRegistry observationRegistry() {
        return ObservationRegistry.create();
    }
}
package com.example.employeemanagementsystem;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Here you would typically return the current logged-in user
        // For simplicity, we're returning a fixed value
        return Optional.of("System");
    }
}

package com.NewsAggregation.NewsApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {
    @Value("${app.auth.username}")
    private String username;

    @Value("${app.auth.password}")
    private String password;

    @Value("${app.auth.role}")
    private String role;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/signup").permitAll().requestMatchers("/api/auth/login").authenticated().anyRequest().authenticated())
                .httpBasic(customizer -> {}).build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername(username).password("{noop}"+ password).roles(role).build();
        return new InMemoryUserDetailsManager(user);
    }
}


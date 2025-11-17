package com.ms.usuario.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // libera POST /usuarios sem autenticação
                .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                // todas as outras requisições exigem autenticação
                .anyRequest().authenticated()
            )
            // Basic Auth atualizado para Spring Security 6.2+
            .httpBasic(httpBasic -> {});

        return http.build();
    }
}




package com.grepp.nbe1_1_clone_mw1.global.configuration;

import com.sun.net.httpserver.HttpsConfigurator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final CsrfConfiguration csrfConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
//                .csrf(AbstractHttpConfigurer::disable);
                .csrf(httpSecurityCsrfConfigurer -> {
                    httpSecurityCsrfConfigurer.csrfTokenRequestHandler(csrfConfiguration.requestAttributeHandler());
                    httpSecurityCsrfConfigurer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                });

        http
                .authorizeHttpRequests((authorize) -> {
                    authorize
                            .requestMatchers("/api/v1/products/**").hasRole("ADMIN")
                            .anyRequest().permitAll();
                });

        return http.build();
    }
}

package com.jbohorquez.emazon_hexagonal.infrastructure.adapters.securityconfig;


import com.jbohorquez.emazon_hexagonal.infrastructure.adapters.jwtconfiguration.JwtAuthenticationFilter;
import com.jbohorquez.emazon_hexagonal.infrastructure.adapters.jwtentrypoint.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class ConfigFilter {

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) throws Exception {

        return http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers(V3_API).permitAll()
                .antMatchers(AUTH).permitAll()
                .antMatchers(SWAGGER_UI).permitAll()
                .antMatchers(SWAGGER_UI_RESOURCES).permitAll()
                .antMatchers(ALL_API).authenticated()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
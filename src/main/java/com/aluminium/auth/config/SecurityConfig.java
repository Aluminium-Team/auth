package com.aluminium.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
     * Example of end points protection: (Remove this comment when finish from here)
     *
     * .authorizeHttpRequests(request -> request
              .antMatchers("/public/**").permitAll()                // Allow public access to any endpoint starting with /public
              .antMatchers("/admin/**").hasRole("ADMIN")            // Only users with the role ADMIN can access /admin/**
              .antMatchers("/user/**").hasRole("USER")              // Only users with the role USER can access /user/**
              .antMatchers("/api/**").hasAuthority("SCOPE_API")     // Users with specific authority can access /api/**
              .anyRequest().authenticated())                        // All other endpoints require authentication
      */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.anyRequest().permitAll())
//                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}

package com.project.airplanebooking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, ex) -> {
                            response.setStatus(403);
                            response.getWriter().write("Unauthorized: " + ex.getMessage());
                        }))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Authentication endpoints (supporting both path patterns)
                        .requestMatchers("/auth/**", "/api/v1/auth/**").permitAll()

                        // Swagger documentation
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        // Health check
                        .requestMatchers("/actuator/health").permitAll()

                        // Public read-only endpoints
                        .requestMatchers(HttpMethod.GET, "/api/v1/flights/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/airlines/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/airports/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/airplanes/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/flights/search/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/flights/search/one-way").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/flights/search/round-trip").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/flights/search/one-way/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/flights/search/round-trip/**").permitAll()

                        // Any other publicly accessible endpoints
                        .requestMatchers(HttpMethod.GET, "/api/v1/seats/available/**").permitAll()

                        // All other requests need authentication
                        .anyRequest().authenticated());

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
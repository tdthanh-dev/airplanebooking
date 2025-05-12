package com.project.airplanebooking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean // localhost:8080/swagger-ui.html
        public OpenAPI schoolManagementOpenAPI() {
                return new OpenAPI()
                                .components(new Components()
                                                .addSecuritySchemes("bearer-jwt",
                                                                new SecurityScheme()
                                                                                .type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer")
                                                                                .bearerFormat("JWT")
                                                                                .in(SecurityScheme.In.HEADER)
                                                                                .name("Authorization")))
                                .info(new Info()
                                                .title("Airplane Booking API")
                                                .description("API cho hệ thống đặt chỗ máy bay")
                                                .version("2.0")
                                                .contact(new Contact()
                                                                .name("tdthanh.dev")
                                                                .email("tdthanh.dev2025@gmail.com"))
                                                .license(new License()
                                                                .name("tdthanh.dev")
                                                                .url("https://tdthanh.dev.vn")));
        }

        @Bean
        public OpenApiCustomizer authEndpointsCustomizer() {
                return openApi -> {
                        openApi.getPaths().forEach((path, item) -> {
                                if (path.startsWith("/auth") || path.startsWith("/api/v1/auth")) {
                                        // Remove security requirements for auth endpoints
                                        for (Operation operation : item.readOperations()) {
                                                operation.setSecurity(null);
                                        }
                                } else {
                                        // Add security requirements for all other endpoints
                                        for (Operation operation : item.readOperations()) {
                                                operation.addSecurityItem(
                                                                new SecurityRequirement().addList("bearer-jwt"));
                                        }
                                }
                        });
                };
        }
}
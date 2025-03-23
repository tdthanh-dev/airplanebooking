package com.project.airplanebooking.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean // localhost:8080/swagger-ui.html
        public OpenAPI schoolManagementOpenAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("Airplane Booking API")
                                                .description("API cho hệ thống đặt chỗ máy bay")
                                                .version("1.0")
                                                .contact(new Contact()
                                                                .name("Admin")
                                                                .email("admin@example.com"))
                                                .license(new License()
                                                                .name("License")
                                                                .url("https://example.com")));
        }
}
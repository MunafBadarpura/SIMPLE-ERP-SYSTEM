package com.munaf.ERP_SYSTEM.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenApi() {
        return new OpenAPI()
                .info(
                new Info()
                        .version("1.0.0")
                        .title("ERP SYSTEM APIs")
                        .description("This ERP system is designed for businesses to manage their operations easily. It includes features like user login, customer and supplier management, product sales tracking, and account balance and transaction monitoring.")
                );
    }

}

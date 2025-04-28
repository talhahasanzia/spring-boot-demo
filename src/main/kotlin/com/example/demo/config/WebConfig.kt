package com.example.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                // Remove Swagger-specific CORS mappings
                // registry.addMapping("/v3/api-docs")
                //     .allowedOrigins("http://localhost:8080")
                //     .allowedMethods("GET", "OPTIONS")
                // registry.addMapping("/swagger-ui/**")
                //     .allowedOrigins("http://localhost:8080")
                //     .allowedMethods("GET", "OPTIONS")
            }
        }
    }
}

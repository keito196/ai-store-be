package com.example.ai_store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Áp dụng CORS cho tất cả các endpoint
                .allowedOrigins("http://localhost:3000")  // Cho phép từ domain của Next.js
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Các phương thức HTTP cho phép
                .allowedHeaders("*")  // Cho phép tất cả các header
                .allowCredentials(true);  // Cho phép gửi cookies (nếu cần)
    }
}
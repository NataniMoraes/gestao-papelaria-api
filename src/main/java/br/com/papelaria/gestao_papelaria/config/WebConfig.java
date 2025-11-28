package br.com.papelaria.gestao_papelaria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite para todos os endpoints
                // Lista de origens permitidas
                .allowedOrigins(
                        "http://localhost:5173", // Projeto Web
                        "http://localhost:8081"  // Projeto Mobile - Porta padrão do Expo Web
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
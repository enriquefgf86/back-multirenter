package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MultirenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultirenterApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/cubancoder/multirenter/**").allowedOrigins("*")
						.allowedMethods("PUT", "DELETE","POST","GET")
						.allowedHeaders("application/json", "text/plain", "application/x-www-form-urlencoded")
						.allowCredentials(false).maxAge(3600);
			}
		};
	}

}

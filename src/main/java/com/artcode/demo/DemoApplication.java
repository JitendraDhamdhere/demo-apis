package com.artcode.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .anyRequest().permitAll()
	        );
	    return http.build();
	    
		/*
		 * http .csrf(csrf -> csrf.disable()) .authorizeHttpRequests(auth -> auth
		 * .requestMatchers("/swagger-ui/index.html", "/v3/api-docs/**") .permitAll()
		 * .anyRequest().authenticated()); return http.build();
		 */
	}
	
}

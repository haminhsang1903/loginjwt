package com.example.demoJWT;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class AddCorsMapping implements WebMvcConfigurer{
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	            //.allowedOrigins("http://localhost:4200")
	        	.allowedOrigins("*")
	            .allowedMethods("GET","POST","PUT","DELETE")
	            .allowedHeaders("*")
	            .allowCredentials(false)
	            .maxAge(3600);
	    }

}

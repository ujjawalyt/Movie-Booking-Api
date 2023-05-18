package com.movie.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	 @Bean
	    public Docket bookHotelApi() {
	        return new Docket( DocumentationType.SWAGGER_2)
	                .select()
	                .apis( RequestHandlerSelectors.any())
	                .paths( PathSelectors.any())
	                .build()
	                .apiInfo(getApiInfo());
	    }

	    private ApiInfo getApiInfo() {
	        return new ApiInfoBuilder()
	                .title("Movie-Booking-Api")
	                .version("1.0")
	                .description("Some description here..")
	                .contact(new Contact("Ujjawal Prakash", "https://github.com/ujjawalyt/Movie-Booking-Api", "ujjawalyt@gmail.com"))
	                .license("Ujjawal License Version 2.0")
	                .build();
	    }
}

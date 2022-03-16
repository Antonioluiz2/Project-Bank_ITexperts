package com.itexperts.java.projeto.config;

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
//import com.itexperts.java.projeto.config.SwaggerConfig.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	public @interface EnableSwagger2 {

	}


	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.itexperts.project"))
                //.apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(showMetadata());
    }


    private ApiInfo showMetadata() {
        return new ApiInfoBuilder()
                .title("Bank REST API")
                .description(" Final Project - IT Experts")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Laura Xavier", "https://github.com/lauragirdwood/", "azevedoemello@gmail.com"))
                .build();
    }
}

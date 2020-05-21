package com.cathy.challenge.keypadcombination.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    public static final String API_TITLE = "Api for keypad combinations generation";
    @Value("${api.version:v1}")
    private String apiVersion;

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(API_TITLE)
                        .version(apiVersion)
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cathy.challenge.keypadcombination"))
                .paths(PathSelectors.any())
                .build();
    }
}

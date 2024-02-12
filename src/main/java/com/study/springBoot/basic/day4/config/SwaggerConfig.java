package com.study.springBoot.basic.day4.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("스프링부트 기초")
                        .description("스프링부트 기초 학습을 위한 프로젝트 입니다.")
                        .version("1.1.1"));
    }

}
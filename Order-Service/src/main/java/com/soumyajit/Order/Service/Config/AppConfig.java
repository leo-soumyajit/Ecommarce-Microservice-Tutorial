package com.soumyajit.Order.Service.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    ModelMapper getBean(){
        return new ModelMapper();
    }
}

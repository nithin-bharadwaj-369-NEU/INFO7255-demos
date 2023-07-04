package com.bharadwaj.demotwo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.bharadwaj.demotwo.model")
public class AppConfig {

//    @Bean
//    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
//        return new ShallowEtagHeaderFilter();
//    }
}

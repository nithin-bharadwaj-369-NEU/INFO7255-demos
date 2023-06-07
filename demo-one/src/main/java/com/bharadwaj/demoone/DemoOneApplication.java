package com.bharadwaj.demoone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@SpringBootApplication
public class DemoOneApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoOneApplication.class, args);
	}
}

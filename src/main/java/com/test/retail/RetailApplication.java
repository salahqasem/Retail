package com.test.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.test.retail.controller","com.test.retail.service"})
@EntityScan("com.test.retail.model")
public class RetailApplication {
	public static void main(String[] args) {
		SpringApplication.run(RetailApplication.class, args);
	}
}

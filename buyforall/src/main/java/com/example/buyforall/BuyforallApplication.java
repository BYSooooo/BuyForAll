package com.example.buyforall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class BuyforallApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyforallApplication.class, args);
	}

}

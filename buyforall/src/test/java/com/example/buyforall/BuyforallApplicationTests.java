package com.example.buyforall;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "com.example")
@WebAppConfiguration
@SpringBootTest
class BuyforallApplicationTests {



	@Test
	void contextLoads() {
	}

}

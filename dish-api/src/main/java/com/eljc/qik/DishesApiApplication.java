package com.eljc.qik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DishesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DishesApiApplication.class, args);
	}

}

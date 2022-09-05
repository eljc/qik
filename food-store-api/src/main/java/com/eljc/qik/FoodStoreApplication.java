package com.eljc.qik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FoodStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodStoreApplication.class, args);
	}
}

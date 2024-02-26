package com.tecdata.spaceship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tecdata.spaceship")
@EnableCaching
public class SpaceShipApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceShipApplication.class, args);
	}

}

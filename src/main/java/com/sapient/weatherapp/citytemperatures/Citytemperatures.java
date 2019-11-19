package com.sapient.weatherapp.citytemperatures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.sapient.weatherapp")
@SpringBootApplication
public class Citytemperatures {

	public static void main(String[] args) {
		SpringApplication.run(Citytemperatures.class, args);
	}

}

package com.sapient.weatherapp.weathernext3days;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.sapient.weatherapp")
@SpringBootApplication
public class WeatherNext3daysApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherNext3daysApplication.class, args);
	}

}

package com.sapient.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.sapient.weather.entity.Weather;
import com.sapient.weather.error.WeatherError;
import com.sapient.weatherapp.service.WeatherService;

@RestController
public class WeatherControllerImpl{
	@Autowired
	WeatherService		service;
	
	@GetMapping("/{cityname}")
	public Weather getWeatherInfo(@PathVariable("cityname") String city) {
		//System.out.println("Found City = "+city);
		return service.getWeatherForCity(city);
	}
	
	@ExceptionHandler
	public ResponseEntity<WeatherError> handleException(WeatherDataParseException e){
		WeatherError error = new WeatherError();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setMessage(e.getMessage());
		
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler
	public ResponseEntity<WeatherError> handleException(RestClientException e){
		WeatherError error = new WeatherError();
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setMessage(e.getMessage());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);		
	}	
}

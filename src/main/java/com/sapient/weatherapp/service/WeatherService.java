package com.sapient.weatherapp.service;

import com.sapient.weather.entity.Weather;

public interface WeatherService {
	public Weather getWeatherForCity(String city);
}

package com.sapient.weather.entity;

import java.util.ArrayList;
import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
	private String cityName;
	private List<NextDayForecast>  forecasts;
	
	public Weather() {
		this.forecasts = new ArrayList<NextDayForecast>(4);
	}
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public List<NextDayForecast> getForecasts() {
		return forecasts;
	}
	public void setForecasts(List<NextDayForecast> forecasts) {
		this.forecasts = forecasts;
	}
	
}

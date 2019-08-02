package com.sapient.weatherapp.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sapient.weather.entity.NextDayForecast;
import com.sapient.weather.entity.Weather;
import com.sapient.weather.error.WeatherDataParseException;
import com.sapient.weather.webclients.RestWebClient;
import com.sapient.weather.webclients.WebClientFactory;

@Service
public class WeatherServiceImpl implements WeatherService{
	private String apiUrl = "http://api.openweathermap.org/data/2.5/forecast?q=";
	private static final String appId = "&appid=d2929e9483efc82c82c32ee7e02d563e"; //appId
	private static final String responseType = "&mode=json&units=metric"; //mode
	
	private ObjectMapper mapper;
	
	public WeatherServiceImpl(@Autowired ObjectMapper mapper) {
		this.mapper = mapper;
		this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}
	
	@Override
	public Weather getWeatherForCity(String city) {
		RestWebClient restClient = WebClientFactory.createRestClient();
		
		String reqUrl = new StringBuilder(apiUrl)
							.append(city)
							.append(responseType)
							.append(appId).toString();
		
		ResponseEntity<String> response = restClient.getUrl(reqUrl);
		return getEntityForResponse(response.getBody(), mapper);
	}
	
	//Data binding
	private Weather getEntityForResponse(String response,ObjectMapper mapper) {
		Weather weather = new Weather();
		try {
			JsonNode  root =  mapper.readTree(response);
			weather.setCityName(root.path("city").path("name").asText());
			
			JsonNode forecastList =  root.path("list");
			buildWeatherForecast(weather,forecastList);
			
		} catch (IOException e) {
			throw new WeatherDataParseException(e.getMessage());
		}
		return weather;
	}

	private void buildWeatherForecast(Weather weather, JsonNode forecastList){
		Map<Integer,List<NextDayForecast>>  forecastMapper = new HashMap();
		
		try {
			for(JsonNode entry:forecastList) {
				String dateTime = entry.path("dt_txt").asText();
				int temp_max = entry.path("main").path("temp_max").asInt();
				int humidity_level = entry.path("main").path("humidity").asInt();
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = format.parse(dateTime);
				
				if(forecastMapper.get(date.getDate()) == null) {
					forecastMapper.put(date.getDate(),new ArrayList<NextDayForecast>());
				}				
				forecastMapper.get(date.getDate()).add(new NextDayForecast(dateTime,humidity_level,temp_max));
			}
		}catch(ParseException e) {
			throw new WeatherDataParseException(e.getMessage());
		}
		
		//Now figure out the average weather
		for(Integer index:forecastMapper.keySet()) {
			List<NextDayForecast> dayForecastList = forecastMapper.get(index);
			int intSize = weather.getForecasts().size();
			
			dayForecastList.forEach(element-> forecastFilter(weather,element));
			int finalSize = weather.getForecasts().size();
		
			if(finalSize ==intSize ) {
				NextDayForecast dayForecast = dayForecastList.get(0);
				dayForecast.setAction("Normal Day. Enjoy!");
				weather.getForecasts().add(dayForecast);
			}
			
			if(weather.getForecasts().size()==3) break;
		}
	}

	private void forecastFilter(Weather weather, NextDayForecast forecast) {
		if(forecast.getMax_temp()>40) {
			forecast.setAction("Use sunscreen lotion");
			weather.getForecasts().add(forecast);
		}
		else if (forecast.getAvg_humidity()>91){
			forecast.setAction("Carry umbrella");	
			weather.getForecasts().add(forecast);
		}
		//skip otherwise		
	}
}

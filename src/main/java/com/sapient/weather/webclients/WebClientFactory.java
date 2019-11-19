package com.sapient.weather.webclients;

import org.springframework.web.client.RestTemplate;

public class WebClientFactory {
	
	public static RestWebClient createRestClient() {
		return new RestWebClient(new RestTemplate());
	}
}

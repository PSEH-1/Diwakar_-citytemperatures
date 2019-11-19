package com.sapient.weather.webclients;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestWebClient extends AbstractWebClient<RestTemplate>{
	
	public RestWebClient(RestTemplate parent) {
		super(parent);
	}

	public ResponseEntity<String> getUrl(String url) throws RestClientException {
		RestTemplate client = getParent();
		ResponseEntity<String> response = client.getForEntity(url, String.class);
		return response;
	}
}

package com.sapient.weatherapp.weathernext3days.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.weather.entity.Weather;
import com.sapient.weatherapp.service.WeatherServiceImpl;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherServiceImpl.class)
public class WeatherServiceTest extends TestCase{
	
	@Autowired
	private ObjectMapper mapper;
	
	@Before
	public void setUp() throws Exception{
		super.setUp();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

    @Test
    public void testGetEntityForResponse() throws Exception{ 
    	File sampleFile = new File(WeatherServiceTest.class.getClassLoader()
    												 .getResource("sampleJsonResponse.json")
    												 .getFile());
    	String fileContent = readFromFile(sampleFile);
    	
    	WeatherServiceImpl service = new WeatherServiceImpl(mapper);
    	Weather responseEntity = service.getEntityForResponse(fileContent, mapper);
    	
    	assertEquals(3, responseEntity.getForecasts().size());
    	assertEquals("London",responseEntity.getCityName());
    }

	private String readFromFile(File sampleFile) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(sampleFile)); 
  
		StringBuilder response = new StringBuilder(); 
		String st= null;
		while((st = br.readLine()) != null) {
			response.append(st);
		}
		return response.toString();
	}
}

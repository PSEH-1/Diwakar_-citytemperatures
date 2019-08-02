package com.sapient.weather.entity;

public class NextDayForecast {
	private String action;
	private int avg_humidity;
	private double max_temp;
	private String dt_text;
	
	public NextDayForecast(String dt_text,int avg_humidity,double max_temp){
		this.dt_text = dt_text;
		this.avg_humidity = avg_humidity;
		this.max_temp = max_temp;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getAvg_humidity() {
		return avg_humidity;
	}
	public void setAvg_humidity(int avg_humidity) {
		this.avg_humidity = avg_humidity;
	}
	public double getMax_temp() {
		return max_temp;
	}
	public void setMax_temp(double max_temp) {
		this.max_temp = max_temp;
	}
	public String getDt_text() {
		return dt_text;
	}
	public void setDt_text(String dt_text) {
		this.dt_text = dt_text;
	}
	
	
}

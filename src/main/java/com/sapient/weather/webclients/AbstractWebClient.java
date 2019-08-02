package com.sapient.weather.webclients;

public class  AbstractWebClient<T> {
	private T parent;
	
	public AbstractWebClient(T parent) {
		this.parent = parent;
	}

	public T getParent() {
		return parent;
	}
}

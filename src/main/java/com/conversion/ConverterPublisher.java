package com.conversion;

import javax.xml.ws.Endpoint;


public class ConverterPublisher {
	public static void main(String[] args) {
		System.out.println("Service is going to publish....");
		Endpoint.publish("http://localhost:8484/ws/converter",new ConverterImpl());
		System.out.println("Service started....");
	}
}

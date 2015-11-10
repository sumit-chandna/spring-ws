package org.skcorg.webservice.configuration;

import org.skcorg.webservice.client.OrderServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientAppConfig {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("org.skcorg.webservices.schemas.jaxb");
		return marshaller;
	}

	@Bean
	public OrderServiceClient orderServiceClient(Jaxb2Marshaller marshaller) {
		OrderServiceClient client = new OrderServiceClient();
		client.setDefaultUri("http://localhost:8080/endpoint/orderService/orderservice.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
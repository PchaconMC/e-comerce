package com.ecomerce.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EcomerceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomerceGatewayApplication.class, args);
	}

}

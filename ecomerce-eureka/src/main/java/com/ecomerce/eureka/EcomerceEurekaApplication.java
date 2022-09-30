package com.ecomerce.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EcomerceEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomerceEurekaApplication.class, args);
	}

}

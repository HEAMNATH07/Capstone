package com.example.ManagementGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ManagementGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementGatewayApplication.class, args);
	}

}

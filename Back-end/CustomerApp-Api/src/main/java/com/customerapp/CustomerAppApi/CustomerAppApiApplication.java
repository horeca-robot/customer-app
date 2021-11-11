package com.customerapp.CustomerAppApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.customerapp.CustomerAppApi.databaselibrary.models")
@EnableJpaRepositories("com.customerapp.CustomerAppApi.databaselibrary")
public class CustomerAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAppApiApplication.class, args);
	}
}
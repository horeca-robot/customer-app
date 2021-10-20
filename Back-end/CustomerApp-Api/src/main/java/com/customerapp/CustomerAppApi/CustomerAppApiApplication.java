package com.customerapp.CustomerAppApi;

import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
import java.util.logging.Logger;

@SpringBootApplication
@EntityScan("edu.fontys.horecarobot.databaselibrary.models")
@EnableJpaRepositories("edu.fontys.horecarobot.databaselibrary.repositories")
public class CustomerAppApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(CustomerAppApiApplication.class, args);
	}
}
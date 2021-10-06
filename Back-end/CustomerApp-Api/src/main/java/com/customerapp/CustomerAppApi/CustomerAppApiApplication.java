package com.customerapp.CustomerAppApi;

import edu.fontys.horecarobot.databaselibrary.HibernateUtil;
import edu.fontys.horecarobot.databaselibrary.models.Product;
import edu.fontys.horecarobot.databaselibrary.models.Tag;
import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;
import java.util.logging.Logger;

@SpringBootApplication
public class CustomerAppApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(CustomerAppApiApplication.class, args);
	}
}
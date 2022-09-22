package com.example.mayankdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


// To remove login page again, and again we had to add exclude for the timebeing
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MayankdemoApplication implements CommandLineRunner {

	@Autowired
	private ServerProperties serverProperties;

	@Override
	public void run(String... args) {
		System.out.println(serverProperties);
	}

	public static void main(String[] args) {
		SpringApplication.run(MayankdemoApplication.class, args);
	}

}

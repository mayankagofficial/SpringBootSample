package com.example.mayankdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


// To remove login page again, and again we had to add exclude for the timebeing
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@SpringBootApplication()
public class MayankdemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(MayankdemoApplication.class, args);
	}
}

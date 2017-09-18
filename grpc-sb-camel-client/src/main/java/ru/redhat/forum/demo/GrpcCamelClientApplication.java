package ru.redhat.forum.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcCamelClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcCamelClientApplication.class, args);
	}
}

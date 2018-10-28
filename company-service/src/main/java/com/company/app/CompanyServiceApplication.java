package com.company.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages="com.company.*")
public class CompanyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyServiceApplication.class, args);
	}
}

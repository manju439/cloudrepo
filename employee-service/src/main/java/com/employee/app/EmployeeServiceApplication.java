package com.employee.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableCircuitBreaker
@ComponentScan(basePackages="com.employee.*")
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
}

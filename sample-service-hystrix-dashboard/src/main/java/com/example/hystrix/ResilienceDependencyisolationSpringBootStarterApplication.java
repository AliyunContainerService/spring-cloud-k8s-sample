package com.example.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class ResilienceDependencyisolationSpringBootStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResilienceDependencyisolationSpringBootStarterApplication.class, args);
	}
}

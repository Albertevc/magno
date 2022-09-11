package br.com.magna.sr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ApiServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServiceRegistryApplication.class, args);
	}

}

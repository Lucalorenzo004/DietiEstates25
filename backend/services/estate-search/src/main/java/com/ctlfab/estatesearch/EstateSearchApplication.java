package com.ctlfab.estatesearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpClient;

@SpringBootApplication
@RestController
@EnableCaching
public class EstateSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstateSearchApplication.class, args);
	}

	@Bean
	public HttpClient getHttpClient() { return HttpClient.newHttpClient();}
}
